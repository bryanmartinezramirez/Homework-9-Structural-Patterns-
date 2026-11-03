package com.example;

import java.util.ArrayList;
import java.util.List;

public class RealSongService implements SongService {

    private final List<Song> songs;

    public RealSongService(List<Song> songs) {
        this.songs = new ArrayList<>(songs);
    }

    @Override
    public Song searchById(Integer songID) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Song song : songs) {
            if (song.getSongID().equals(songID)) {
                return song;
            }
        }

        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Song> results = new ArrayList<>();

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                results.add(song);
            }
        }

        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Song> results = new ArrayList<>();

        for (Song song : songs) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                results.add(song);
            }
        }

        return results;
    }
}
