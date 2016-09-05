package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.DemoPersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by Mickey on 05.09.2016.
 */

@Component
public class DemoPersonsService {

    Hashtable<Long, DemoPersonEntity> persons = new Hashtable<Long, DemoPersonEntity>();
    public DemoPersonsService() {

        DemoPersonEntity p = new DemoPersonEntity();
        p.setId(1L);
        p.setName("Путин");
        persons.put(1L, p);

        p = new DemoPersonEntity();
        p.setId(2L);
        p.setName("Медведев");
        persons.put(2L, p);

        p = new DemoPersonEntity();
        p.setId(3L);
        p.setName("Навальный");
        persons.put(3L, p);
    }
    public DemoPersonEntity getPersons (Long id){
        if (persons.containsKey(id))
            return persons.get(id);
        else
            return null;
    }
    public Hashtable<Long, DemoPersonEntity> getAll(){ return persons; }

    @Autowired
    private PersonRepository repository;
}