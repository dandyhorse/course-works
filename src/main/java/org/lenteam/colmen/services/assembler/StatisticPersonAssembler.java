package org.lenteam.colmen.services.assembler;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.models.StatisticPerson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@Component
public class StatisticPersonAssembler implements Assembler<StatisticPerson, PersonEntity> {
    @Override
    public StatisticPerson newModel(PersonEntity entity) {
        Long fullRank = entity.getRanks().stream()
                .map(PersonPageRankEntity::getRank).map(Integer::longValue)
                .reduce(0L, (i1, i2) -> i1 + i2);
        return new StatisticPerson(entity.getId(), fullRank);
    }

    @Override
    public PersonEntity newEntity(StatisticPerson model) {
        return null;
    }

    @Override
    public Iterable<StatisticPerson> newModelList(List<PersonEntity> entityList) {
        Stream<StatisticPerson> stream = entityList.stream().map(this::newModel);
        return stream.collect(Collectors.toList());
    }

}
