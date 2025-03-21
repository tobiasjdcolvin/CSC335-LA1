package src.model;

import src.store.Song;
import java.util.ArrayList;
import java.util.Collections;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    /*=============================================================================================
     *
     * Getters
     *
     *===========================================================================================*/

    // Returns a deep copy of songs
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs2 = new ArrayList<Song>();
        for (Song song : this.songs) {
            songs2.add(new Song(song));
        }
        return songs2;
    }

    /*=============================================================================================
     *
     * Methods
     *
     *===========================================================================================*/

    public void addSong(Song song) {
        boolean alreadyFound = false;
        for (Song s : this.songs) {
            if (s.getArtist().toLowerCase().equals(song.getArtist().toLowerCase()) &&
            s.getTitle().toLowerCase().equals(song.getTitle().toLowerCase())) {
                alreadyFound = true;
            }
        }

        if (!alreadyFound) {
            songs.add(song);
        }
    }
    public void removeSong(Song song) {
        Song toRemove = null;
        String title = song.getTitle().toLowerCase();
        String artist = song.getArtist().toLowerCase();

        for (Song s : songs) {
            if (s.getTitle().toLowerCase().equals(title) && s.getArtist().toLowerCase().equals(artist)) {
                toRemove = s;
            }
        }

        if (toRemove != null) {
            songs.remove(toRemove);
        }
    }

    public void shuffle() {
        Collections.shuffle(this.songs);
        Collections.shuffle(this.songs);
    }

    /*=============================================================================================
     *
     * Overrides
     *
     *===========================================================================================*/

    @Override
    // Playlist: <Playlist name>\n<Song1> - <Artist1>...
    public String toString() {
        String returnStr = "Playlist: " + this.name + "\n";
        for (Song s : this.songs) {
            returnStr += s.getTitle() + " - " + s.getArtist() + "\n";
        }
        return returnStr;
    }
}
