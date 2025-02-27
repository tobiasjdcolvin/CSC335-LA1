package src.model;

import src.store.Song;
import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }
    public void removeSong(Song song) {
        songs.remove(song);
    }

    protected ArrayList<Song> getSongs() { return songs; }

    @Override
    public String toString() {
        String returnStr = "Playlist:" + " " + this.name + "\n";
        for (Song s : this.songs) {
            returnStr += s.getTitle() + " - " + s.getArtist() + "\n";
        }
        return returnStr;
    }
}
