package src.model;

import src.store.Song;
import java.util.ArrayList;

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
        songs.add(song);
    }
    public void removeSong(Song song) {
        songs.remove(song);
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
