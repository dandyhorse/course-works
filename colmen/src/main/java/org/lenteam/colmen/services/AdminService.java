package org.lenteam.colmen.services;

import org.lenteam.colmen.models.Keyword;
import org.lenteam.colmen.models.Site;
import org.lenteam.colmen.models.StatisticPerson;
import org.lenteam.colmen.services.interfaces.CommonAdminService;
import org.springframework.stereotype.Component;

/**
 * @author Anton_Solovev
 * @since 9/9/2016.
 */
@Component
public class AdminService implements CommonAdminService {

    @Override
    public Iterable<StatisticPerson> savePerson(String name) {
        return null;
    }

    @Override
    public Iterable<StatisticPerson> deletePerson(Integer id) {
        return null;
    }

    @Override
    public Iterable<Site> saveSite(String name) {
        return null;
    }

    @Override
    public Iterable<Site> deleteSite(Integer id) {
        return null;
    }

    @Override
    public Iterable<Keyword> getKeysByPerson(Integer id) {
        return null;
    }

    @Override
    public Iterable<Keyword> saveKeyword(String keyword, Integer personId) {
        return null;
    }

    @Override
    public Iterable<Keyword> deleteKeyword(Integer id) {
        return null;
    }
}
