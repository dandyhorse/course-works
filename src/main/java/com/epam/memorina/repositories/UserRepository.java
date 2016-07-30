package com.epam.memorina.repositories;

import com.epam.memorina.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Solovev Anton
 * @since 26.07.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByName(String name);

}
