package dev.stefanteunissen.restserver.exceptions;

public class UserIncorrectDetailsEnteredException extends RuntimeException {
    public UserIncorrectDetailsEnteredException(String message) {
        super(message);
    }
}
