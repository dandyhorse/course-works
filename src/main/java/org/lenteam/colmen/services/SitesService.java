package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by Mickey on 05.09.2016.
 */

@Component
public class SitesService {
    Hashtable<Long, SiteEntity> sites = new Hashtable<Long, SiteEntity>();
    public SitesService() {

        SiteEntity p = new SiteEntity();
        p.setId(1L);
        p.setName("1tv.ru");
        sites.put(1L, p);

        p = new SiteEntity();
        p.setId(2L);
        p.setName("lenta.ru");
        sites.put(2L, p);

        p = new SiteEntity();
        p.setId(3L);
        p.setName("gazeta.ru");
        sites.put(3L, p);
    }
    public SiteEntity getSites (Long id){
        if (sites.containsKey(id))
            return sites.get(id);
        else
            return null;
    }
    public Hashtable<Long, SiteEntity> getAll(){ return sites; }

    @Autowired
    private PersonRepository repository;
}
