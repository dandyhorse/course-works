package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
import org.lenteam.colmen.repositories.PersonRepository;
import org.lenteam.colmen.repositories.SiteRepository;

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
        return null;
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(PersonEntity person, SiteEntity site) {
        return null;
    }
}
