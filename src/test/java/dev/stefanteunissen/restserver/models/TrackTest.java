package dev.stefanteunissen.restserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TrackTest {
    private Track sut;

    @BeforeEach
    void setUp() {
        sut = new Track();
    }

    @Test
    void returnCorrectDateFormatSevenNovember2004() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("07-09-2004");

        sut.setPublicationDate(date);

        assertEquals("07-09-2004", sut.getPublicationDate());
    }

    @Test
    void returnId1() {
        sut.setId(1);
        assertEquals(1, sut.getId());
    }

    @Test
    void returnTitleAtWorldsEnd() {
        sut.setTitle("At World's End");
        assertEquals("At World's End", sut.getTitle());
    }

    @Test
    void returnPerformerAdept() {
        sut.setPerformer("Adept");
        assertEquals("Adept", sut.getPerformer());
    }

    @Test
    void returnDuration263() {
        sut.setDuration(263);
        assertEquals(263, sut.getDuration());
    }

    @Test
    void returnPlaycount50000() {
        sut.setPlaycount(50000);
        assertEquals(50000, sut.getPlaycount());
    }

    @Test
    void returnDescriptionNewSong2011() {
        sut.setDescription("New song 2011");
        assertEquals("New song 2011", sut.getDescription());
    }

    @Test
    void returnOfflineAvailableTrue() {
        sut.setOfflineAvailable(true);
        assertTrue(sut.getOfflineAvailable());
    }

    @Test
    void returnAlbumDeathDealers() {
        sut.setAlbum("Death Dealers");
        assertEquals("Death Dealers", sut.getAlbum());
    }

    @Test
    void returnAlbumDeathDealersWithDifferentConstructor() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("07-09-2004");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        sut = new Track(
                1,
                "At World's End",
                "Adept",
                263,
                "Death Dealers",
                50000,
                cal,
                "New song 2011",
                true
        );
        sut.setAlbum("Death Dealers");
        assertEquals("Death Dealers", sut.getAlbum());
    }
}
