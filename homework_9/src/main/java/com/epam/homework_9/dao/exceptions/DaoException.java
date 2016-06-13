package com.epam.homework_9.dao.exceptions;

public class DaoException extends RuntimeException {
    public DaoException(String s, Throwable t) {
        super(s, t);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
