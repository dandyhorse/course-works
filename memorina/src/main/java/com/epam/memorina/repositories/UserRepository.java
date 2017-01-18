package com.epam.memorina.repositories;

import com.epam.memorina.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Solovev Anton
 * @since 26.07.2016.
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM com.epam.memorina.entities.UserEntity u WHERE u.username = ?1")
    boolean existsBy(String username);

}
