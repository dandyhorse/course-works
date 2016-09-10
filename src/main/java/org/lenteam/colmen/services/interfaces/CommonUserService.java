package org.lenteam.colmen.services.interfaces;

import org.lenteam.colmen.models.DailyStatistic;
import org.lenteam.colmen.models.Person;
import org.lenteam.colmen.models.Site;
import org.lenteam.colmen.models.StatisticPerson;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public interface CommonUserService {

    Iterable<Person> getAllPersons();

    Iterable<Site> getAllSites();

    Iterable<StatisticPerson> getPersonsOnSite(Long site);

    DailyStatistic getPersonStatisticOnSite(Long personId, Long siteId);

}

