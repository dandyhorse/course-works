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

import java.util.HashSet;
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
    private Assembler<Site, SiteEntity> siteAssembler;
    @Autowired
    private Assembler<StatisticPerson, PersonEntity> statisticPersonAssembler;
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

    @Override
    public Iterable<StatisticPerson> getPersonsOnSite(Long siteId) {
        SiteEntity site = siteRepository.findOne(siteId);
        Set<PersonEntity> persons = new HashSet<>();

        Set<PageEntity> pages = site.getPages();
        for (PageEntity page : pages) {
            Set<PersonPageRankEntity> personPageRanks = page.getRanks();
            persons.addAll(personPageRanks.stream()
                    .map(PersonPageRankEntity::getPerson)
                    .collect(Collectors.toList()));
        }
        return statisticPersonAssembler.newModelList(persons);
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(Long personId, Long siteId) {
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
