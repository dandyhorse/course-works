package org.lenteam.colmen.services.interfaces;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;

/**
 * @author Anton_Solovev
 * @author Mickey
 * @since 9/9/2016.
 */
public interface CommonAdminService {

    Iterable<PersonEntity> savePerson(String name);

    Iterable<PersonEntity> deletePerson(Long id);

    Iterable<SiteEntity> saveSite(String name);

    Iterable<SiteEntity> deleteSite(Long id);

    Iterable<KeywordEntity> getKeysByPerson(Long id);

    Iterable<KeywordEntity> saveKeyword(String keyword, Long personId);

    Iterable<KeywordEntity> deleteKeyword(Long id);

}
