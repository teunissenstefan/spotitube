package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TokenDAO;
import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @Mock
    private TokenDAO mockTokenDAO;
    private TokenService sut;

    @BeforeEach
    void setUp() {
        sut = new TokenService();
        sut.tokenDAO = mockTokenDAO;
    }

    @Test
    void returnCorrectToken() {
        when(mockTokenDAO.setToken(1))
                .thenReturn("token");

        String actualToken = sut.setToken(1);
        verify(mockTokenDAO).setToken(1);
        assertEquals("token", actualToken);
    }
}
