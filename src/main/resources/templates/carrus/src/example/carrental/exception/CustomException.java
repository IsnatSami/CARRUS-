package com.yourcompany.carrental.exception;


/**
 * Custom exception for handling application-specific errors.
 */
public class CustomException extends RuntimeException {

    /**
     * Constructs a new CustomException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Constructs a new CustomException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

