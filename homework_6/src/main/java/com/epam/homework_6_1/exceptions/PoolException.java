package com.epam.homework_6_1.exceptions;

import java.util.NoSuchElementException;

public class PoolException extends NoSuchElementException {

    public PoolException(String message) {
        super(message);
    }

}
