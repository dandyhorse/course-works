package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PageEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
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
        return null;
    }
}
