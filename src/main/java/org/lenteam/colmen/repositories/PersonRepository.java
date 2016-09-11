package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anton_Solovev
 * @since 8/20/2016
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
