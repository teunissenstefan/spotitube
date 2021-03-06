package dev.stefanteunissen.restserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    private User sut;

    @BeforeEach
    void setUp() {
        sut = new User(0,"admin", "pizza");
    }

    @Test
    void returnId1() {
        sut.setId(1);
        assertEquals(1, sut.getId());
    }
    @Test
    void returnId0TestConstructor() {
        assertEquals(0, sut.getId());
    }

    @Test
    void returnUserStefan() {
        sut.setUser("Stefan");
        assertEquals("Stefan", sut.getUser());
    }

    @Test
    void returnCheckPasswordAdmin() {
        sut.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
        assertTrue(sut.passwordCheck("admin"));
    }

}
