package com.epam.memorina.assemblers;

import com.epam.memorina.configs.SpringAsmConfig;
import com.epam.memorina.entities.GameStatisticEntity;
import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.models.GameStatistic;
import com.epam.memorina.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Solovev Anton
 * @since 31.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAsmConfig.class)
public class SimpleUserAssemblerImplTest {

    private static final String DAVE_NAME = "Dave Matthews";
    private static final String DAVE_PASS = "dave_is_cool";

    @Autowired
    private Assembler<User, UserEntity> assembler;
    @Autowired
    private Assembler<GameStatistic, GameStatisticEntity> statisticAssembler;

    private GameStatistic statistic;
    private GameStatisticEntity statisticEntity;

    @Before
    public void setUp() throws Exception {
        statisticEntity = new GameStatisticEntity(15, 1234L);
        statistic = new GameStatistic(10, 1550L);
    }

    @Test
    public void newUserModelTest() throws Exception {
        UserEntity userEntity = new UserEntity(DAVE_NAME, DAVE_PASS, statisticEntity);
        User user = assembler.newModel(userEntity);
        assertThat(user.getUsername(), is(userEntity.getUsername()));
        assertThat(user.getPassword(), is(userEntity.getPassword()));
    }

    @Test
    public void newUserEntityTest() throws Exception {
        User user = new User(DAVE_NAME, DAVE_PASS, statistic);
        UserEntity userEntity = assembler.newEntity(user);
        assertThat(userEntity.getUsername(), is(user.getUsername()));
        assertThat(userEntity.getPassword(), is(user.getPassword()));
    }

    @Test
    public void newStatisticEntityTest() throws Exception {
        GameStatisticEntity entity = statisticAssembler.newEntity(statistic);
        assertThat(statistic.getGamesFinishedCount(), is(entity.getGamesFinishedCount()));
        assertThat(statistic.getTimeInGame(), is(entity.getTimeInGame()));
    }

    @Test
    public void newStatisticModelTest() throws Exception {
        GameStatistic gameStatistic = statisticAssembler.newModel(statisticEntity);
        assertThat(statisticEntity.getGamesFinishedCount(), is(gameStatistic.getGamesFinishedCount()));
        assertThat(statisticEntity.getTimeInGame(), is(gameStatistic.getTimeInGame()));
    }
}
