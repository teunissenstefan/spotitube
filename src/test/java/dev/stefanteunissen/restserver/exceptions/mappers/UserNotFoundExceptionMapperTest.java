package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionMapperTest {
    private UserNotFoundExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new UserNotFoundExceptionMapper();
    }

    @Test
    void return404ForUserNotFound() {
        Response actualResult = sut.toResponse(new UserNotFoundException(""));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),actualResult.getStatus());
    }
}
