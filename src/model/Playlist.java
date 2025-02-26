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

    @Override
    public String toString() {
        String returnStr = "Playlist:" + " " + this.name + "\n";
        for (Song s : this.songs) {
            returnStr += s.getTitle() + " - " + s.getArtist() + "\n";
        }
        return returnStr;
    }
}
