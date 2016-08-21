package org.lenteam.colmen.services;

import org.lenteam.colmen.entites.PersonEntity;
import org.lenteam.colmen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 */
@Component
public class DefaultService {

    @Autowired
    private PersonRepository repository;

    public PersonEntity findOne(Long id) {
        return repository.findOne(id);
    }

    public void saveWithName(String name) {
        repository.save(new PersonEntity(name));
    }
}
