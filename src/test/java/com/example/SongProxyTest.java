package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SongProxyTest {

    static class CountingServer implements SongService {

        int idCalls = 0;
        int titleCalls = 0;
        int albumCalls = 0;

        Song song = new Song(
            1, "Blinding Lights",
            "The Weeknd", "After Hours", 200);

        @Override
        public Song searchById(Integer songID) {
            idCalls++;
            return songID == 1 ? song : null;
        }

        @Override
        public List<Song> searchByTitle(String title) {
            titleCalls++;

            if (song.getTitle().equalsIgnoreCase(title)) {
                return List.of(song);
            }

            return List.of();
        }

        @Override
        public List<Song> searchByAlbum(String album) {
            albumCalls++;

            if (song.getAlbum().equalsIgnoreCase(album)) {
                return List.of(song);
            }

            return List.of();
        }
    }

    @Test
    void testCacheById() {
        CountingServer server = new CountingServer();
        SongService proxy = new SongProxy(server);

        Song first = proxy.searchById(1);
        Song second = proxy.searchById(1);

        assertSame(first, second);
        assertEquals(1, server.idCalls);
    }

    @Test
    void testCacheByTitle() {
        CountingServer server = new CountingServer();
        SongService proxy = new SongProxy(server);

        List<Song> first =
            proxy.searchByTitle("Blinding Lights");

        List<Song> second =
            proxy.searchByTitle("blinding lights");

        assertEquals(1, first.size());
        assertSame(first, second);
        assertEquals(1, server.titleCalls);
    }

    @Test
    void testCacheByAlbum() {
        CountingServer server = new CountingServer();
        SongService proxy = new SongProxy(server);

        List<Song> first =
            proxy.searchByAlbum("After Hours");

        List<Song> second =
            proxy.searchByAlbum("after hours");

        assertEquals(1, first.size());
        assertSame(first, second);
        assertEquals(1, server.albumCalls);
    }

    @Test
    void testMissingSongIsCached() {
        CountingServer server = new CountingServer();
        SongService proxy = new SongProxy(server);

        assertNull(proxy.searchById(999));
        assertNull(proxy.searchById(999));

        assertEquals(1, server.idCalls);
    }
}
