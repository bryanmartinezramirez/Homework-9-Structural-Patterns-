package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @Test
    void testSongMetadata() {
        Song song = new Song(
            1, "Stronger", "Kanye West", "Graduation", 311);

        assertEquals(1, song.getSongID());
        assertEquals("Stronger", song.getTitle());
        assertEquals("Kanye West", song.getArtist());
        assertEquals("Graduation", song.getAlbum());
        assertEquals(311, song.getDuration());

        assertTrue(song.toString().contains("Stronger"));
        assertTrue(song.toString().contains("Kanye West"));
    }
}
