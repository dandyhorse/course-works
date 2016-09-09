package org.lenteam.colmen.services.interfaces;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public interface CommonUserService {

    Iterable<PersonEntity> getAllPersons();

    Iterable<SiteEntity> getAllSites();

    Iterable<PersonEntity> getPersonsOnSite(Long site);

    DailyStatistic getPersonStatisticOnSite(Long personId, Long siteId);

}

