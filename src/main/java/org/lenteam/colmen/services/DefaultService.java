package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.DailyStatistic;
import org.lenteam.colmen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 *
 * this class will be deleted
 */

@Component
public class DefaultService implements CommonUserService {

    HashMap<Long, PersonEntity> persons = new HashMap<Long, PersonEntity>();

    public DefaultService() {

        PersonEntity p = new PersonEntity();
        p.setId(1L);
        p.setName("Путин");
        persons.put(1L, p);

        p = new PersonEntity();
        p.setId(2L);
        p.setName("Медведев");
        persons.put(2L, p);

        p = new PersonEntity();
        p.setId(3L);
        p.setName("Навальный");
        persons.put(3L, p);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Autowired
    private PersonRepository repository;

    public PersonEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<PersonEntity> getAllPersons() {
        return null;
    }

    @Override
    public Iterable<PersonEntity> getPersons(Long id) { return null;}

    @Override
    public Iterable<SiteEntity> getAllSites() {
        return null;
    }

    @Override
    public Iterable<PersonEntity> getPersonsOnSite(SiteEntity site) {
        return null;
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(PersonEntity person, SiteEntity site) {
        return null;
    }

    @Override
    public Iterable<SiteEntity> saveWithName(String name) {return null; }

    @Override
    public Iterable<PersonEntity> savePerson (String name) {return null;}

    public PersonEntity getPerson(Long id) {
        if (persons.containsKey(id))
            return persons.get(id);
        else
            return null;
    }
     public HashMap<Long, PersonEntity> getAll(){ return persons; }

     //public void saveWithName(String name) { repository.save(new PersonEntity(name)); }
}
