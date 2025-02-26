package src.model;

import src.store.Album;
import src.store.MusicStore;
import src.store.Song;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryModel {
    private MusicStore store;

    private HashMap<String, ArrayList<Song>> songsByTitle;
    private HashMap<String, ArrayList<Song>> songsByArtist;
    private HashMap<String, ArrayList<Album>> albumsByTitle;
    private HashMap<String, ArrayList<Album>> albumsByArtist;

    // list of playlists:
    private HashMap<String, Playlist> playlists;

    // constructor:
    public LibraryModel(MusicStore store) {
        this.store = store;

        this.songsByTitle = new HashMap<String, ArrayList<Song>>();
        this.songsByArtist = new HashMap<String, ArrayList<Song>>();
        this.albumsByTitle = new HashMap<String, ArrayList<Album>>();
        this.albumsByArtist = new HashMap<String, ArrayList<Album>>();

        this.playlists = new HashMap<String, Playlist>();
    }


    // returns an ArrayList of Song objects if found, null if not
    public ArrayList<Song> getSongsByTitle(String songTitle) {
        songTitle = songTitle.toLowerCase();
        if (songsByTitle.containsKey(songTitle)) {
            return songsByTitle.get(songTitle);
        } else {
            return null;
        }
    }

    // returns an ArrayList of Song objects if found, null if not
    public ArrayList<Song> getSongsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        if (songsByArtist.containsKey(artistName)) {
            return songsByArtist.get(artistName);
        } else {
            return null;
        }
    }

    // returns an ArrayList of Album objects if found, null if not
    public ArrayList<Album> getAlbumsByTitle(String albumTitle) {
        albumTitle = albumTitle.toLowerCase();
        if (albumsByTitle.containsKey(albumTitle)) {
            return albumsByTitle.get(albumTitle);
        } else {
            return null;
        }
    }

    // returns an ArrayList of Album objects if found, null if not
    public ArrayList<Album> getAlbumsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        if (albumsByArtist.containsKey(artistName)) {
            return albumsByArtist.get(artistName);
        } else {
            return null;
        }
    }

    // search for a playlist by name,
    // returns a Playlist object if found, null if not
    public Playlist getPlaylistByName(String playlistName) {
        if (playlists.containsKey(playlistName)) {
            return playlists.get(playlistName);
        } else {
            return null;
        }
    }

    // add a song to the library:
    public boolean addSongToLibrary(String songName, String artistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();

        // first, check if the song exists in the store:
        ArrayList<Song> potentialSongs = store.getSongsByTitle(songName);
        Song found = null;
        for (Song s : potentialSongs) {
            if (s.getArtist().equals(artistName)) {
                found = s;
            }
        }

        // if the song exists in the store, add it to the library
        if (found != null) {
            if (!songsByArtist.containsKey(artistName)) {
                songsByArtist.put(artistName, new ArrayList<Song>());
            }
            songsByArtist.get(artistName).add(found);
            if (!songsByTitle.containsKey(songName)) {
                songsByTitle.put(songName, new ArrayList<Song>());
            }
            songsByTitle.get(songName).add(found);
        }

        // returns true if found and false if null
        return (found != null);
    }
}
