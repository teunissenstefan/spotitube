package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TrackDAO;
import dev.stefanteunissen.restserver.models.Playlist;
import dev.stefanteunissen.restserver.models.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest {
    @Mock
    private TrackDAO mockTrackDAO;
    private TrackService sut;

    @BeforeEach
    void setUp() {
        sut = new TrackService();
        sut.trackDAO = mockTrackDAO;
    }

    @Test
    void returnCorrectTracksGetAllTracksForPlaylist() throws ParseException {
        ArrayList<Track> allTracks = new ArrayList<>();
        allTracks.add(new Track());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("07-09-2004"));
        allTracks.add(new Track(0, "Lied", "", 2, "", 2, cal, "", false));

        when(mockTrackDAO.getAllTracksForPlaylist(0))
                .thenReturn(allTracks);

        ArrayList<Track> actualAllTracks = sut.getAllTracksForPlaylist(0);
        verify(mockTrackDAO).getAllTracksForPlaylist(0);
        assertEquals(allTracks, actualAllTracks);
    }

    @Test
    void returnCorrectTracksGetAllTracks() throws ParseException {
        ArrayList<Track> allTracks = new ArrayList<>();
        allTracks.add(new Track());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("07-09-2004"));
        allTracks.add(new Track(0, "Lied", "", 2, "", 2, cal, "", false));

        when(mockTrackDAO.getAllTracks())
                .thenReturn(allTracks);

        ArrayList<Track> actualAllTracks = sut.getAllTracks();
        verify(mockTrackDAO).getAllTracks();
        assertEquals(allTracks, actualAllTracks);
    }

    @Test
    void returnTrueDeleteTrackFromPlaylist() throws ParseException {
        when(mockTrackDAO.deleteTrackFromPlaylist(0,0))
                .thenReturn(true);

        boolean actualBoolean = sut.deleteTrackFromPlaylist(0,0);
        verify(mockTrackDAO).deleteTrackFromPlaylist(0,0);
        assertTrue(actualBoolean);
    }

    @Test
    void returnCorrectTracksGetAllTracksFromPlaylist() throws ParseException {
        ArrayList<Track> allTracks = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("07-09-2004");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Track tr1 = new Track(0,"Lied","",2,"",2,cal,"",false);
        allTracks.add(tr1);

        when(mockTrackDAO.getAllTracksFromPlaylist(0))
                .thenReturn(allTracks);

        Map<String, Object> map = new HashMap<>();
        int totalLength = 0;
        for (Track plist : allTracks)
            totalLength += plist.getDuration();
        map.put("tracks", allTracks);
        map.put("length", totalLength);

        Map<String, Object> actualAllTracksFromPlaylist = sut.getAllTracksFromPlaylist(0);
        verify(mockTrackDAO).getAllTracksFromPlaylist(0);
        assertEquals(map,actualAllTracksFromPlaylist);
    }
}
