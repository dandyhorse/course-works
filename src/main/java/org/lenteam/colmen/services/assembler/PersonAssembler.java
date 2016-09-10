package org.lenteam.colmen.services.assembler;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.models.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@Component
public class PersonAssembler implements Assembler<Person, PersonEntity> {
    @Override
    public Person newModel(PersonEntity entity) {
        return new Person(entity.getId(), entity.getName());
    }

    @Override
    public PersonEntity newEntity(Person model) {
        return null;
    }

    @Override
    public Iterable<Person> newModelList(Iterable<PersonEntity> entityList) {
        List<PersonEntity> list = (List<PersonEntity>) entityList;
        Stream<Person> stream = list.stream().map(this::newModel);
        return stream.collect(Collectors.toList());
    }
}
