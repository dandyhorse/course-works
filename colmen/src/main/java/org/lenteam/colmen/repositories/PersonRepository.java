package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton_Solovev
 * @since 8/20/2016
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer>, JpaSpecificationExecutor {
}
