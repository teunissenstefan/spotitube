package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostLoginRequest;
import dev.stefanteunissen.restserver.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {
    @Mock
    private UserService userServiceMock;
    private UserResource sut;

    @BeforeEach
    void setUp() {
        sut = new UserResource();
        sut.userService = userServiceMock;
    }

    @Test
    void return200GetUserById() {
        User expectedUser = new User(1, "Stefan", "password");
        when(userServiceMock.getUserById(1)).thenReturn(expectedUser);

        Response actualResponse = sut.getUserById(1, "token");

        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return200GetAllUsersAsJSON() {
        ArrayList<User> allUsers = new ArrayList<>();
        User expectedUser = new User(1, "Stefan", "password");
        allUsers.add(expectedUser);

        when(userServiceMock.getAllUsers()).thenReturn(allUsers);

        Response actualResponse = sut.getAllUsersAsJson("token");

        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }
}