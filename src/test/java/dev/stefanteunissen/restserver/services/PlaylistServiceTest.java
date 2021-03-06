package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.PlaylistDAO;
import dev.stefanteunissen.restserver.data.TrackDAO;
import dev.stefanteunissen.restserver.models.Playlist;
import dev.stefanteunissen.restserver.models.Track;
import dev.stefanteunissen.restserver.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    @Mock
    private PlaylistDAO mockPlaylistDAO;
    @Mock
    private TrackDAO mockTrackDAO;
    private PlaylistService sut;

    @BeforeEach
    void setUp() {
        sut = new PlaylistService();
        sut.trackDAO = mockTrackDAO;
        sut.playlistDAO = mockPlaylistDAO;
    }

    @Test
    void returnTrueAddTrackToPlaylist() {
        when(mockPlaylistDAO.addTrackToPlaylist(0, 0, true))
                .thenReturn(true);

        boolean actualBoolean = sut.addTrackToPlaylist(0, 0, true);
        verify(mockPlaylistDAO).addTrackToPlaylist(0, 0, true);
        assertTrue(actualBoolean);
    }

    @Test
    void returnTrueAddPlaylist() {
        when(mockPlaylistDAO.addPlaylist(0, "Playlist"))
                .thenReturn(true);

        boolean actualBoolean = sut.addPlaylist(0, "Playlist");
        verify(mockPlaylistDAO).addPlaylist(0, "Playlist");
        assertTrue(actualBoolean);
    }

    @Test
    void returnTrueUpdatePlaylist() {
        when(mockPlaylistDAO.updatePlaylist(0, "Playlist"))
                .thenReturn(true);

        boolean actualBoolean = sut.updatePlaylist(0, "Playlist");
        verify(mockPlaylistDAO).updatePlaylist(0, "Playlist");
        assertTrue(actualBoolean);
    }

    @Test
    void returnTrueDeletePlaylist() {
        when(mockPlaylistDAO.deletePlaylist(0))
                .thenReturn(true);

        boolean actualBoolean = sut.deletePlaylist(0);
        verify(mockPlaylistDAO).deletePlaylist(0);
        assertTrue(actualBoolean);
    }

    @Test
    void returnCorrectPlaylistGetAllPlaylists() throws ParseException {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        Playlist pl1 = new Playlist();
        pl1.setId(1);
        pl1.setName("Pietje Bell");
        Playlist pl2 = new Playlist();
        pl2.setId(2);
        pl2.setName("Bismarck");
        allPlaylists.add(pl1);
        allPlaylists.add(pl2);

        ArrayList<Track> playlist1Tracks = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("07-09-2004");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Track tr1 = new Track(0,"Lied","",2,"",2,cal,"",false);
        playlist1Tracks.add(tr1);

        when(mockPlaylistDAO.getAllPlaylists())
                .thenReturn(allPlaylists);
        when(mockTrackDAO.getAllTracksFromPlaylist(pl1.getId()))
                .thenReturn(playlist1Tracks);
        when(mockTrackDAO.getAllTracksFromPlaylist(pl2.getId()))
                .thenReturn(new ArrayList<>());

        ArrayList<Playlist> actualAllPlaylists = sut.getAllPlaylists(0);
        verify(mockPlaylistDAO).getAllPlaylists();
        verify(mockTrackDAO).getAllTracksFromPlaylist(pl1.getId());
        verify(mockTrackDAO).getAllTracksFromPlaylist(pl2.getId());
        assertEquals("Bismarck",actualAllPlaylists.get(1).getName());
        assertEquals("Lied",actualAllPlaylists.get(0).getTracks().get(0).getTitle());
    }

    @Test
    void returnCorrectLengthPlaylistsGetAllPlaylistsWithLength() throws ParseException {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        Playlist pl1 = new Playlist();
        pl1.setId(1);
        pl1.setName("Pietje Bell");
        Playlist pl2 = new Playlist();
        pl2.setId(2);
        pl2.setName("Bismarck");
        allPlaylists.add(pl1);
        allPlaylists.add(pl2);

        ArrayList<Track> playlist1Tracks = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("07-09-2004");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Track tr1 = new Track(0,"Lied","",2,"",2,cal,"",false);
        playlist1Tracks.add(tr1);

        when(sut.getAllPlaylists(0))
                .thenReturn(allPlaylists);
        when(mockTrackDAO.getAllTracksFromPlaylist(pl1.getId()))
                .thenReturn(playlist1Tracks);
        when(mockTrackDAO.getAllTracksFromPlaylist(pl2.getId()))
                .thenReturn(new ArrayList<>());

        Map<String, Object> actualAllPlaylistsWithLength = sut.getAllPlaylistsWithLength(0);
        verify(mockTrackDAO).getAllTracksFromPlaylist(pl1.getId());
        verify(mockTrackDAO).getAllTracksFromPlaylist(pl2.getId());
        assertEquals(2,actualAllPlaylistsWithLength.get("length"));
    }
}
