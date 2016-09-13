package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PageEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.*;
import org.lenteam.colmen.repositories.PersonRepository;
import org.lenteam.colmen.repositories.SiteRepository;
import org.lenteam.colmen.services.assembler.Assembler;
import org.lenteam.colmen.services.interfaces.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rinat
 * @author Anton_Solovev
 */
@Component
public class UserService implements CommonUserService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private Assembler<Site, SiteEntity> siteAssembler;
    @Autowired
    private Assembler<StatisticPerson, PersonEntity> statisticPersonAssembler;
    @Autowired
    private Assembler<Person, PersonEntity> personAssembler;
    @Autowired
    private EntityManager manager;

    @Override
    public Iterable<Person> getAllPersons() {
        Iterable<PersonEntity> entities = personRepository.findAll();
        return personAssembler.newModelList(entities);
    }

    @Override
    public Iterable<Site> getAllSites() {
        Iterable<SiteEntity> entities = siteRepository.findAll();
        return siteAssembler.newModelList(entities);
    }

    @SuppressWarnings("unsafe")
    @Override
    public Iterable<StatisticPerson> getPersonsOnSite(Integer siteId) {
        Query q = manager.createNativeQuery(
                "select PersonID, sum(Rank) " +
                        "from Pages p, PersonPageRank r " +
                        "where p.ID = r.PageID AND p.SiteID = :id " +
                        "group by PersonID, SiteID").setParameter("id", siteId);
        List<StatisticPerson> persons = new ArrayList<>();
        List<Integer[]> resultList = q.getResultList();
        persons.addAll(resultList.stream()
                .map(i -> new StatisticPerson(i[0], i[1]))
                .collect(Collectors.toList()));
        return persons;
    }

//    @SuppressWarnings("unsafe")
//    @Override
//    public Iterable<StatisticPerson> getPersonsOnSite(Integer siteId) {
//        List<StatisticPerson> persons;
//        List<Integer[]> personMapRank = personRepository.sumPersonPageRanksBySite(siteId);
//        if (!personMapRank.isEmpty()) {
//            Stream<StatisticPerson> statisticPersonStream = personMapRank.stream().map(i -> {
//                if (i.length != 2)
//                    throw new RuntimeException("PersonEntity not unique im map-rank");
//                return new StatisticPerson(i[0], i[1]);
//            });
//            persons = statisticPersonStream.collect(Collectors.toList());
//        } else {
//            persons = new LinkedList<>();
//        }
//        return persons;
//    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(Integer personId, Integer siteId) {
        Set<PageStatistic> pageStatistics = new HashSet<>();

        SiteEntity site = siteRepository.findOne(siteId);
        PersonEntity person = personRepository.findOne(personId);

        Set<PageEntity> pagesOnSite = site.getPages(); //получаем все страницы, которые находятся на сайте
        Set<PersonPageRankEntity> ranks = person.getRanks(); //получаем рейтинги личности на всех страницах

        for (PersonPageRankEntity rank : ranks) {
            PageEntity personPage = rank.getPage(); //получаем страницу для каждого рейтинга

            // если страница принадлежит сайту, записываем ее в статистику
            if (pagesOnSite.contains(personPage)) {
                pageStatistics.add(new PageStatistic(
                        personPage.getUrl(),
                        personPage.getLastScanDate().toLocalDate(),
                        rank.getRank()
                ));
            }
        }
        return new DailyStatistic(person.getName(), site.getName(), pageStatistics);
    }
}
