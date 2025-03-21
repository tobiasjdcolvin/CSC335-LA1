package src.store;

import java.util.ArrayList;

public class Album {
    private final String title;
    private final String artist;
    private final String genre;
    private final ArrayList<Song> songs;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    // Main constructor
    public Album(String title, String artist, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.songs = new ArrayList<Song>();
    }

    // Copy constructor
    public Album(Album album) {
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.songs = new ArrayList<Song>();

        // Make deep copy of songs
        for (Song song : album.songs) {
            this.songs.add(new Song(song));
        }
    }

    /*=============================================================================================
     *
     * Methods
     *
     *===========================================================================================*/

    public void addSong(Song song) {
        this.songs.add(song);
    }

    /*=============================================================================================
     *
     * Getters
     *
     *===========================================================================================*/

    public String getTitle() {
        return this.title;
    }
    public String getArtist() {
        return this.artist;
    }
    public String getGenre() {return this.genre; }
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        for (Song song : this.songs) {
            songs.add(new Song(song));
        }
        return songs;
    }

    public void removeSong(String title, String artist) {
        Song sToRemove = null;
        for (Song s : this.songs) {
            if(s.getTitle().toLowerCase().equals(title.toLowerCase()) && s.getArtist().toLowerCase()
                    .equals(artist.toLowerCase())) {
                sToRemove = s;
            }
        }
        if (sToRemove != null) {
            this.songs.remove(sToRemove);
        }
    }

    /*=============================================================================================
     *
     * Overrides
     *
     *===========================================================================================*/

    @Override
    // <Album name>: <Artist>\n<Song1>\n<Song2>...
    public String toString() {
        String ret = this.title + ": " + this.artist;
        for (Song song : this.songs) {
            ret = ret + "\n" + song.getTitle();
        }
        return ret;
    }

}
