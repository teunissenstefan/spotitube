package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.data.UserDAO;
import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostLoginRequest;
import dev.stefanteunissen.restserver.services.LoginService;
import dev.stefanteunissen.restserver.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {
    @Mock
    private LoginService loginServiceMock;
    @Mock
    private TokenService tokenServiceMock;
    @Mock
    private UserDAO userDAOMock;
    private LoginResource sut;

    @BeforeEach
    void setUp() {
        sut = new LoginResource();
        sut.loginService = loginServiceMock;
        sut.tokenService = tokenServiceMock;
        sut.loginService.userDAO = userDAOMock;
    }

    @Test
    void return201Login() {
        PostLoginRequest postLoginRequest = new PostLoginRequest();
        postLoginRequest.password = "admin";
        postLoginRequest.user = "admin";

        when(loginServiceMock.login(postLoginRequest.user, postLoginRequest.password))
                .thenReturn(new User(1, postLoginRequest.user, postLoginRequest.password));

        Response actualResponse = sut.postLogin(postLoginRequest);
        verify(loginServiceMock).login(postLoginRequest.user, postLoginRequest.password);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }
}
