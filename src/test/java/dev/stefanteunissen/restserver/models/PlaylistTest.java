package dev.stefanteunissen.restserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PlaylistTest {
    private Playlist sut;

    @BeforeEach
    void setUp() {
        sut = new Playlist();
    }

    @Test
    void returnId1() {
        sut.setId(1);
        assertEquals(1, sut.getId());
    }

    @Test
    void returnNameMyList() {
        sut.setName("My List");
        assertEquals("My List", sut.getName());
    }

    @Test
    void returnOwnerId1() {
        sut.setOwnerId(1);
        assertEquals(1, sut.getOwnerId());
    }

    @Test
    void returnOwnerTrue() {
        sut.setOwner(true);
        assertTrue(sut.isOwner());
    }

    @Test
    void returnSetTracks() {
        ArrayList<Track> tracks = new ArrayList<>();
        Track track = new Track();
        tracks.add(track);

        sut.setTracks(tracks);
        assertEquals(tracks, sut.getTracks());
    }

    @Test
    void returnAddTrack() {
        Track track = new Track();

        sut.addTrack(track);
        assertEquals(track, sut.getTracks().get(0));
    }
}
