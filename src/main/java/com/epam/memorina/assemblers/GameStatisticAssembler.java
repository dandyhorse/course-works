package com.epam.memorina.assemblers;

import com.epam.memorina.entities.GameStatisticEntity;
import com.epam.memorina.models.GameStatistic;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Solovev Anton
 * @since 31.07.2016.
 */
@Component
public class GameStatisticAssembler implements Assembler<GameStatistic, GameStatisticEntity> {

    @Override
    public GameStatistic newModel(GameStatisticEntity entity) {
        GameStatistic model = new GameStatistic();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    @Override
    public GameStatisticEntity newEntity(GameStatistic model) {
        GameStatisticEntity entity = new GameStatisticEntity();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

}
