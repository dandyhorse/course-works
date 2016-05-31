package com.epam.homework_8.dao.exceptions;

public class EntityException extends RuntimeException {

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String s, Throwable e) {
        super(s, e);
    }
}
