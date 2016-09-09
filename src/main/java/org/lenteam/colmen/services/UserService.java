package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PageEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
import org.lenteam.colmen.models.PageStatistic;
import org.lenteam.colmen.repositories.PersonRepository;
import org.lenteam.colmen.repositories.SiteRepository;

import java.util.HashSet;
import java.util.Set;

public class UserService implements CommonUserService {

    PersonRepository personRepository;
    SiteRepository siteRepository;

    @Override
    public Iterable<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Iterable<SiteEntity> getAllSites() {
        return siteRepository.findAll();
    }

    @Override
    public Iterable<PersonEntity> getPersonsOnSite(SiteEntity site) {
        Set<PersonEntity> persons = new HashSet<>();

        Set<PageEntity> pages = site.getPages();
        for (PageEntity page : pages) {
            Set<PersonPageRankEntity> personPageRanks = page.getRanks();
            for (PersonPageRankEntity personPageRank : personPageRanks) {
                persons.add(personPageRank.getPerson());
            }
        }
        return persons;
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(PersonEntity person, SiteEntity site) {

        Set<PageStatistic> pageStatistics = new HashSet<>();

        Set<PageEntity> sitePages = site.getPages(); //получаем все страницы, которые находятся на сайте

        Set<PersonPageRankEntity> personRanks = person.getRanks(); //получаем рейтинги личности на всех страницах

        for (PersonPageRankEntity personPageRankEntity : personRanks) {
            PageEntity pageOnPersonRank = personPageRankEntity.getPage(); //получаем страницу для каждого рейтинга

            // если страница принадлежит сайту, записываем ее в статистику
            if (sitePages.contains(pageOnPersonRank)) {
                pageStatistics.add(new PageStatistic(
                        pageOnPersonRank.getUrl(), pageOnPersonRank.getLastScanDate(), personPageRankEntity.getRank()));
            }
        }
        return new DailyStatistic(person.getName(), site.getName(), pageStatistics);
    }
}
