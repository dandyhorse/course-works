package com.epam.memorina.exceptions;

/**
 * @author Solovev Anton
 * @since 01.08.2016.
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
