package org.lenteam.colmen.services.assembler;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
public interface Assembler<M, E> {

    M newModel(E entity);

    E newEntity(M model);

    Iterable<M> newModelList(Iterable<E> entityList);
}
