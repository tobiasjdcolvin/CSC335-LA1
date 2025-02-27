package src.store;

import java.util.ArrayList;

public class Album {
    private final String title;
    private final String artist;
    private ArrayList<Song> songs;

    /* Constructors */
    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public Album(Album album) {
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.songs = new ArrayList<Song>();

        // Make deep copy of songs
        for (Song song : album.songs) {
            this.songs.add(new Song(song));
        }
    }

    /* Methods */
    public void addSong(Song song) {
        this.songs.add(song);
    }

    /* Getters */
    public String getTitle() {
        return this.title;
    }
    public String getArtist() {
        return this.artist;
    }

    /* Overrides */
    @Override
    public String toString() {
        String ret = this.title + ": " + this.artist;
        for (Song song : this.songs) {
            ret = ret + "\n" + song.getTitle();
        }
        return ret;
    }

}
