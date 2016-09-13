package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PageEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.*;
import org.lenteam.colmen.repositories.NativeRepository;
import org.lenteam.colmen.repositories.PersonRepository;
import org.lenteam.colmen.repositories.SiteRepository;
import org.lenteam.colmen.services.assembler.Assembler;
import org.lenteam.colmen.services.interfaces.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private NativeRepository nativeRepository;
    @Autowired
    private Assembler<Site, SiteEntity> siteAssembler;
    @Autowired
    private Assembler<Person, PersonEntity> personAssembler;

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
        List<Object[]> resultList = nativeRepository.sumPersonPageRanksBySite(siteId);
        List<StatisticPerson> persons = new ArrayList<>();
        persons.addAll(resultList.stream()
                .map(o -> {
                    Integer i = (Integer) o[0];
                    BigInteger bigI = (BigInteger) o[1];
                    long value = bigI.longValue();
                    return new StatisticPerson(i, value);
                })
                .collect(Collectors.toList()));
        return persons;
    }

//    @Override
//    public DailyStatistic getPersonStatisticOnSite(Integer personId, Integer siteId) {
//        Set<PageStatistic> pageStatistics = new HashSet<>();
//
//        SiteEntity site = siteRepository.findOne(siteId);
//        PersonEntity person = personRepository.findOne(personId);
//
//        Set<PageEntity> pagesOnSite = site.getPages(); //получаем все страницы, которые находятся на сайте
//        Set<PersonPageRankEntity> ranks = person.getRanks(); //получаем рейтинги личности на всех страницах
//
//        for (PersonPageRankEntity rank : ranks) {
//            PageEntity personPage = rank.getPage(); //получаем страницу для каждого рейтинга
//
//            // если страница принадлежит сайту, записываем ее в статистику
//            if (pagesOnSite.contains(personPage)) {
//                pageStatistics.add(new PageStatistic(
//                        personPage.getUrl(),
//                        personPage.getLastScanDate().toLocalDate(),
//                        rank.getRank()
//                ));
//            }
//        }
//        return new DailyStatistic(person.getName(), site.getName(), pageStatistics);
//    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(Integer personId, Integer siteId) {
        
        return null;
    }
}
