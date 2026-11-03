package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class SongProxy implements SongService {

    private final SongService realServer;

    private final Map<Integer, Optional<Song>> idCache =
        new HashMap<>();

    private final Map<String, List<Song>> titleCache =
        new HashMap<>();

    private final Map<String, List<Song>> albumCache =
        new HashMap<>();

    public SongProxy(SongService realServer) {
        this.realServer = realServer;
    }

    @Override
    public Song searchById(Integer songID) {

        if (!idCache.containsKey(songID)) {
            Song song = realServer.searchById(songID);
            idCache.put(songID, Optional.ofNullable(song));
        }

        return idCache.get(songID).orElse(null);
    }

    @Override
    public List<Song> searchByTitle(String title) {

        String key = title.toLowerCase(Locale.ROOT);

        if (!titleCache.containsKey(key)) {
            List<Song> results =
                realServer.searchByTitle(title);

            titleCache.put(key, List.copyOf(results));
        }

        return titleCache.get(key);
    }

    @Override
    public List<Song> searchByAlbum(String album) {

        String key = album.toLowerCase(Locale.ROOT);

        if (!albumCache.containsKey(key)) {
            List<Song> results =
                realServer.searchByAlbum(album);

            albumCache.put(key, List.copyOf(results));
        }

        return albumCache.get(key);
    }
}
