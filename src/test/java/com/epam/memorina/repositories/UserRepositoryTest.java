package com.epam.memorina.repositories;

import com.epam.memorina.configs.SpringJpaConfig;
import com.epam.memorina.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void getAllTest() throws Exception {
        UserEntity dave = new UserEntity("Dave Matthews");
        dave = repository.save(dave);

        UserEntity carter = new UserEntity("Carter Beauford");
        carter = repository.save(carter);

        List<UserEntity> result = (List<UserEntity>) repository.findAll();
        assertThat(result.size(), is(2));
        assertThat(result, hasItem(dave));
    }
}