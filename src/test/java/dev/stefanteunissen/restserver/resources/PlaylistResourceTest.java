package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostPlaylistRequest;
import dev.stefanteunissen.restserver.requests.PostTrackToPlaylistRequest;
import dev.stefanteunissen.restserver.services.PlaylistService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {
    @Mock
    private PlaylistService playlistServiceMock;
    @Mock
    private TrackService trackServiceMock;
    @Mock
    private UserService userServiceMock;
    private PlaylistResource sut;
    private final String token = "6b16ff40-2be7-4224-a025-36295397ccda";

    @BeforeEach
    void setUp() {
        sut = new PlaylistResource();
        sut.playlistService = playlistServiceMock;
        sut.trackService = trackServiceMock;
        sut.userService = userServiceMock;
    }

    @Test
    void return200DeletePlaylistOnPlaylistServiceInvoked() {
        when(userServiceMock.getUserByToken(token)).thenReturn(new User(1, "admin", "admin"));

        Response actualResponse = sut.deletePlaylist(42, token);

        verify(playlistServiceMock).deletePlaylist(42);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return200GetAllPlaylists() {
        when(userServiceMock.getUserByToken(token)).thenReturn(new User(1, "admin", "admin"));

        Response actualResponse = sut.getAllPlaylists(token);
        verify(playlistServiceMock).getAllPlaylistsWithLength(1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return200GetAllTracksFromPlaylist() {
        Response actualResponse = sut.getAllTracksFromPlaylist(1, token);
        verify(trackServiceMock).getAllTracksFromPlaylist(1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return200DeleteTrackFromPlaylist() {
        Response actualResponse = sut.deleteTrackFromPlaylist(1, 1, token);
        verify(trackServiceMock).deleteTrackFromPlaylist(1, 1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return200UpdatePlaylist() {
        when(userServiceMock.getUserByToken(token)).thenReturn(new User(1, "admin", "admin"));

        PostPlaylistRequest postPlaylistRequest = new PostPlaylistRequest();
        postPlaylistRequest.name = "Playlist";
        postPlaylistRequest.id = 1;
        Response actualResponse = sut.updatePlaylist(postPlaylistRequest.id, token, postPlaylistRequest);
        verify(playlistServiceMock).updatePlaylist(postPlaylistRequest.id, postPlaylistRequest.name);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return201AddPlaylist() {
        when(userServiceMock.getUserByToken(token)).thenReturn(new User(1, "admin", "admin"));

        PostPlaylistRequest postPlaylistRequest = new PostPlaylistRequest();
        postPlaylistRequest.name = "Playlist";
        postPlaylistRequest.id = 1;
        Response actualResponse = sut.addPlaylist(token, postPlaylistRequest);
        verify(playlistServiceMock).addPlaylist(postPlaylistRequest.id, postPlaylistRequest.name);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void return201AddTrackToPlaylist() {
        PostTrackToPlaylistRequest postTrackToPlaylistRequest = new PostTrackToPlaylistRequest();
        postTrackToPlaylistRequest.offlineAvailable = false;
        postTrackToPlaylistRequest.id = 1;
        Response actualResponse = sut.addTrackToPlaylist(1, token, postTrackToPlaylistRequest);
        verify(playlistServiceMock).addTrackToPlaylist(1, postTrackToPlaylistRequest.id, postTrackToPlaylistRequest.offlineAvailable);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }
}
