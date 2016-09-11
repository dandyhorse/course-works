package org.lenteam.colmen.services.interfaces;

import org.lenteam.colmen.models.Keyword;
import org.lenteam.colmen.models.StatisticPerson;
import org.lenteam.colmen.models.Site;

/**
 * @author Anton_Solovev
 * @author Mickey
 * @since 9/9/2016.
 */
public interface CommonAdminService {

    Iterable<StatisticPerson> savePerson(String name);

    Iterable<StatisticPerson> deletePerson(Integer id);

    Iterable<Site> saveSite(String name);

    Iterable<Site> deleteSite(Integer id);

    Iterable<Keyword> getKeysByPerson(Integer id);

    Iterable<Keyword> saveKeyword(String keyword, Integer personId);

    Iterable<Keyword> deleteKeyword(Integer id);

}
