package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public interface CommonUserService {

    Iterable<PersonEntity> getAllPersons();
    Iterable<PersonEntity> savePerson(String name);
    Iterable<PersonEntity> deletePerson(Long id);
    Iterable<SiteEntity> getAllSites();
    Iterable<SiteEntity> saveSite(String name);
    Iterable<SiteEntity> deleteSite(Long id);
    Iterable<PersonEntity> getPersonsOnSite(SiteEntity site);
    Iterable<KeywordEntity> getKeysByPerson(PersonEntity id);
    Iterable<KeywordEntity> getAllKeys();
    Iterable<KeywordEntity> saveKeyword(String name, Long id);
    Iterable<KeywordEntity> deleteKeyword(Long id);
    DailyStatistic getPersonStatisticOnSite(PersonEntity person, SiteEntity site);

}

