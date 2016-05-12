package com.epam.homework_3.lists.interfaces;

/**
 * Created by User on 09.05.2016.
 */
@FunctionalInterface
public interface WeirdFunction<T, R> {
    R apply(T t);
}
