package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public interface CommonUserService {

    Iterable<PersonEntity> getAllPersons();
    Iterable<PersonEntity> getPersons(Long id);
    Iterable<PersonEntity> savePerson(String name);
    Iterable<SiteEntity> getAllSites();
    Iterable<SiteEntity> saveWithName(String name);
    Iterable<PersonEntity> getPersonsOnSite(SiteEntity site);
    DailyStatistic getPersonStatisticOnSite(PersonEntity person, SiteEntity site);

}

