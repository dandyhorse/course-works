package com.epam.memorina.repositories;

import com.epam.memorina.configs.SpringJpaConfig;
import com.epam.memorina.entities.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private static final String DAVE_NAME = "Dave Matthews";
    private static final String CARTER_NAME = "Carter Beauford";
    private static final String DAVE_PASS = "dave_is_cool";
    private static final String CARTER_PASS = "dave_sucks";

    private UserEntity dave = new UserEntity();
    private UserEntity carter = new UserEntity();

    @Before
    public void setUp() throws Exception {
        dave.setUsername(DAVE_NAME);
        dave.setPassword(DAVE_PASS);
        carter.setUsername(CARTER_NAME);
        carter.setPassword(CARTER_PASS);

        repository.save(dave);
        repository.save(carter);
    }

    @After
    public void tearDown() throws Exception {
        repository.delete(dave);
        repository.delete(carter);
    }

    @Test
    public void getAllTest() throws Exception {
        List<UserEntity> result = (List<UserEntity>) repository.findAll();
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(dave, carter));
    }

    @Test
    public void finByUsernameTest() throws Exception {
        UserEntity user = repository.findByUsername(DAVE_NAME);
        assertThat(user, is(dave));
    }

    @Test
    public void existsTest() throws Exception {
        boolean exists = repository.existsBy(CARTER_NAME);
        assertThat(exists, is(true));
    }
}