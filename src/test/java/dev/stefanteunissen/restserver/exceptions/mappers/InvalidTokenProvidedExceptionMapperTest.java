package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.exceptions.InvalidTokenProvidedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidTokenProvidedExceptionMapperTest {
    private InvalidTokenProvidedExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new InvalidTokenProvidedExceptionMapper();
    }

    @Test
    void return403ForInvalidTokenProvided() {
        Response actualResult = sut.toResponse(new InvalidTokenProvidedException(""));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(),actualResult.getStatus());
    }
}
