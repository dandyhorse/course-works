package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.KeywordEntity;
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
    public Iterable<PersonEntity> savePerson (String name) {return null;}

    @Override
    public Iterable<KeywordEntity> getKeysByPerson(PersonEntity id) { return null; }

    @Override
    public Iterable<KeywordEntity> getAllKeys() { return null; }

    @Override
    public Iterable<KeywordEntity> saveKeyword(String name, Long id) { return null; }

    @Override
    public Iterable<PersonEntity> deletePerson(Long id) { return null; }

    @Override
    public Iterable<SiteEntity> saveSite(String name) { return null; }

    @Override
    public Iterable<SiteEntity> deleteSite(Long id) { return null; }

    @Override
    public Iterable<KeywordEntity> deleteKeyword(Long id) { return null; }

    public PersonEntity getPerson(Long id) {
        if (persons.containsKey(id))
            return persons.get(id);
        else
            return null;
    }
     public HashMap<Long, PersonEntity> getAll(){ return persons; }

     //public void saveWithName(String name) { repository.save(new PersonEntity(name)); }
}
