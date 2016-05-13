package com.epam.homework_3.lists.interfaces;

@FunctionalInterface
public interface WeirdFunction<T, R> {
    R apply(T t);
}
