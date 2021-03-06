package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.exceptions.UserIncorrectDetailsEnteredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIncorrectDetailsEnteredExceptionMapperTest {
    private UserIncorrectDetailsEnteredExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new UserIncorrectDetailsEnteredExceptionMapper();
    }

    @Test
    void return401ForUserIncorrectDetailsEntered() {
        Response actualResult = sut.toResponse(new UserIncorrectDetailsEnteredException(""));
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(),actualResult.getStatus());
    }
}
