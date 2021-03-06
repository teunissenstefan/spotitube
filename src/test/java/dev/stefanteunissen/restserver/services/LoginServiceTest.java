package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.UserDAO;
import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    private UserDAO userDAOMock;
    private LoginService sut;

    @BeforeEach
    void setUp() {
        sut = new LoginService();
        sut.userDAO = userDAOMock;
    }

    @Test
    void returnUser() {
        PostLoginRequest postLoginRequest = new PostLoginRequest();
        postLoginRequest.password = "admin";
        postLoginRequest.user = "admin";
        User expectedUser = mock(User.class);

        when(userDAOMock.getUserByUsername(postLoginRequest.user))
                .thenReturn(expectedUser);
        when(expectedUser.passwordCheck(postLoginRequest.password))
                .thenReturn(true);

        User actualUser = sut.login(postLoginRequest.user, postLoginRequest.password);
        verify(userDAOMock).getUserByUsername(postLoginRequest.user);
        verify(userDAOMock).getUserByUsername(postLoginRequest.user);
        assertEquals(expectedUser, actualUser);
    }
}
