package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.services.TrackService;
import dev.stefanteunissen.restserver.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrackResourceTest {
    @Mock
    private TrackService trackServiceMock;
    @Mock
    private UserService userServiceMock;
    private TrackResource sut;

    @BeforeEach
    void setUp() {
        sut = new TrackResource();
        sut.trackService = trackServiceMock;
        sut.userService = userServiceMock;
    }

    @Test
    void return200GetAllTracksForPlaylist() {
        Response actualResponse = sut.getAllTracksForPlaylist("token", 1);

        verify(trackServiceMock).getAllTracksForPlaylist(1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }
}
