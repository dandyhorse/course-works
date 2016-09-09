package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.services.interfaces.CommonAdminService;
import org.springframework.stereotype.Component;

/**
 * @author Anton_Solovev
 * @since 9/9/2016.
 */
@Component
public class AdminService implements CommonAdminService {
    @Override
    public Iterable<PersonEntity> savePerson(String name) {
        return null;
    }

    @Override
    public Iterable<PersonEntity> deletePerson(Long id) {
        return null;
    }

    @Override
    public Iterable<SiteEntity> saveSite(String name) {
        return null;
    }

    @Override
    public Iterable<SiteEntity> deleteSite(Long id) {
        return null;
    }

    @Override
    public Iterable<KeywordEntity> getKeysByPerson(Long id) {
        return null;
    }

    @Override
    public Iterable<KeywordEntity> saveKeyword(String keyword, Long personId) {
        return null;
    }

    @Override
    public Iterable<KeywordEntity> deleteKeyword(Long id) {
        return null;
    }
}
