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

    Iterable<StatisticPerson> deletePerson(Long id);

    Iterable<Site> saveSite(String name);

    Iterable<Site> deleteSite(Long id);

    Iterable<Keyword> getKeysByPerson(Long id);

    Iterable<Keyword> saveKeyword(String keyword, Long personId);

    Iterable<Keyword> deleteKeyword(Long id);

}
