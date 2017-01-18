package org.lenteam.colmen.services.assembler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
public interface Assembler<M, E> {

    M newModel(E entity);

    E newEntity(M model);

    Iterable<M> newModelList(List<E> entityList);

    default Iterable<M> newModelList(Iterable<E> entityList) {
        List<E> target = new ArrayList<>();
        entityList.forEach(target::add);
        return newModelList(target);
    }
}
