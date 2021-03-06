package dev.stefanteunissen.restserver.exceptions;

public class InvalidTokenProvidedException extends RuntimeException {
    public InvalidTokenProvidedException(String message) {
        super(message);
    }
}
