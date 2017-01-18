package com.epam.memorina.assemblers;

/**
 * @author Solovev Anton
 * @since 31.07.2016.
 */

public interface Assembler<M, E> {

    M newModel(E entity);

    E newEntity(M model);

}
