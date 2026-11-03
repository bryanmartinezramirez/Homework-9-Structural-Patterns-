package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class RealSongServiceTest {

    private RealSongService createServer() {
        return new RealSongService(List.of(
            new Song(1, "Stronger",
                "Kanye West", "Graduation", 311),

            new Song(2, "Flashing Lights",
                "Kanye West", "Graduation", 237),

            new Song(3, "Heartless",
                "Kanye West", "808s & Heartbreak", 211)
        ));
    }

    @Test
    void testSearchById() {
        RealSongService server = createServer();

        Song result = server.searchById(1);

        assertNotNull(result);
        assertEquals("Stronger", result.getTitle());
        assertEquals("Kanye West", result.getArtist());
    }

    @Test
    void testSearchByTitle() {
        RealSongService server = createServer();

        List<Song> results =
            server.searchByTitle("Flashing Lights");

        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getSongID());
        assertEquals("Kanye West", results.get(0).getArtist());
    }

    @Test
    void testSearchByAlbum() {
        RealSongService server = createServer();

        List<Song> results =
            server.searchByAlbum("Graduation");

        assertEquals(2, results.size());
    }
}
