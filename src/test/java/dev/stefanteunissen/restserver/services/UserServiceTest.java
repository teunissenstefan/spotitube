package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TokenDAO;
import dev.stefanteunissen.restserver.data.UserDAO;
import dev.stefanteunissen.restserver.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDAO mockUserDAO;
    @Mock
    private TokenDAO mockTokenDAO;
    private UserService sut;

    @BeforeEach
    void setUp() {
        sut = new UserService();
        sut.userDAO = mockUserDAO;
        sut.tokenDAO = mockTokenDAO;
    }

    @Test
    void returnCorrectUserGetUserByToken() {
        User user = new User(0, "admin", "admin");

        when(mockTokenDAO.getUserIdByToken("token"))
                .thenReturn(0);
        when(mockUserDAO.getUserById(0))
                .thenReturn(user);

        User actualUser = sut.getUserByToken("token");
        verify(mockTokenDAO).getUserIdByToken("token");
        verify(mockUserDAO).getUserById(0);
        assertEquals(user, actualUser);
    }

    @Test
    void returnCorrectUserGetUserById() {
        User user = new User(0, "admin", "admin");

        when(mockUserDAO.getUserById(0))
                .thenReturn(user);

        User actualUser = sut.getUserById(0);
        verify(mockUserDAO).getUserById(0);
        assertEquals(user, actualUser);
    }

    @Test
    void returnCorrectGetAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.add(new User(0, "admin", "admin"));
        allUsers.add(new User(1, "pizza", "koerier"));

        when(mockUserDAO.getAllUsers())
                .thenReturn(allUsers);

        ArrayList<User> actualAllUsers = sut.getAllUsers();
        verify(mockUserDAO).getAllUsers();
        assertEquals(allUsers, actualAllUsers);
    }
}
