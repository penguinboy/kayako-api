package org.penguin.kayako.exception;


/**
 * Wrapper class for exception which happens during obtaining or parsing response from kayako.
 *
 * @author raynerw
 * @author fatroom
 */
public class ApiResponseException extends Exception {
    public ApiResponseException(String message, Throwable e) {
        super(message, e);
    }
}
