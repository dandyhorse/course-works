package com.epam.memorina.assemblers;

import com.epam.memorina.entities.GameStatisticEntity;
import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.models.GameStatistic;
import com.epam.memorina.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Solovev Anton
 * @since 31.07.2016.
 */
@Component
public class SimpleUserAssemblerImpl implements Assembler<User, UserEntity> {

    private static String[] excludedFieldToCopy = {"gameStatistic"};
    @Autowired
    private Assembler<GameStatistic, GameStatisticEntity> statisticAsm;

    @Override
    public User newModel(UserEntity entity) {
        User user = new User();
        BeanUtils.copyProperties(entity, user, excludedFieldToCopy);
        user.setGameStatistic(statisticAsm.newModel(entity.getGameStatistic()));
        return user;
    }

    @Override
    public UserEntity newEntity(User user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity, excludedFieldToCopy);
        entity.setGameStatistic(statisticAsm.newEntity(user.getGameStatistic()));
        return entity;
    }

}
