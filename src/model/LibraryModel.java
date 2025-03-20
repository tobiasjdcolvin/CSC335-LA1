package src.model;

import src.store.Album;
import src.store.MusicStore;
import src.store.Song;

import java.util.*;

public class LibraryModel {
    private MusicStore store;

    private HashMap<String, ArrayList<Song>> songsByTitle;
    private HashMap<String, ArrayList<Song>> songsByArtist;
    private HashMap<String, ArrayList<Album>> albumsByTitle;
    private HashMap<String, ArrayList<Album>> albumsByArtist;
    private ArrayList<Song> frequentlyPlayedList;

    // playlists:
    private HashMap<String, Playlist> playlists;
    // list of favorite songs:
    private ArrayList<Song> favorites;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    public LibraryModel(MusicStore store) {
        this.store = store;

        this.songsByTitle = new HashMap<String, ArrayList<Song>>();
        this.songsByArtist = new HashMap<String, ArrayList<Song>>();
        this.albumsByTitle = new HashMap<String, ArrayList<Album>>();
        this.albumsByArtist = new HashMap<String, ArrayList<Album>>();

        this.playlists = new HashMap<String, Playlist>();
        this.favorites = new ArrayList<Song>();

        this.frequentlyPlayedList = new ArrayList<Song>();

        this.createPlaylist("recently played");
        this.createPlaylist("frequently played");
    }

    /*=============================================================================================
     *
     * Getters
     *
     *===========================================================================================*/

    // returns an ArrayList of song's sorted by title
    public ArrayList<String> getSongsByTitleSorted() {
        ArrayList<Song> songs = new ArrayList<>();

        for (String title : this.songsByTitle.keySet()) {
            songs.addAll(this.songsByTitle.get(title));
        }

        songs.sort(new CompareSongsByTitle());

        ArrayList<String> ret = new ArrayList<>();

        for (Song song : songs) {
            ret.add(song.toString());
        }

        return ret;
    }

    // Helper classes for comparison
    private class CompareSongsByTitle implements Comparator<Song> {
        public int compare(Song s1, Song s2) {
            return s1.getTitle().compareTo(s2.getTitle());
        }
    }

    // returns an ArrayList of song's sorted by title
    public ArrayList<String> getSongsByArtistSorted() {
        ArrayList<Song> songs = new ArrayList<>();

        for (String title : this.songsByTitle.keySet()) {
            songs.addAll(this.songsByTitle.get(title));
        }

        songs.sort(new CompareSongsByArtist());

        ArrayList<String> ret = new ArrayList<>();

        for (Song song : songs) {
            ret.add(song.toString());
        }

        return ret;
    }

    // Helper classes for comparison
    private class CompareSongsByArtist implements Comparator<Song> {
        public int compare(Song s1, Song s2) {
            return s1.getArtist().compareTo(s2.getArtist());
        }
    }

    // returns an ArrayList of song's sorted by rating
    public ArrayList<String> getSongsByRatingSorted() {
        ArrayList<Song> songs = new ArrayList<>();

        for (String title : this.songsByTitle.keySet()) {
            songs.addAll(this.songsByTitle.get(title));
        }

        songs.sort(new CompareSongsByRating());

        ArrayList<String> ret = new ArrayList<>();

        for (Song song : songs) {
            ret.add(song.toString());
        }

        return ret;
    }

    // Helper classes for comparison
    private class CompareSongsByRating implements Comparator<Song> {
        public int compare(Song s1, Song s2) {
            return s1.getRating() - s2.getRating();
        }
    }

    // returns an ArrayList of song info if found, empty ArrayList if not
    public ArrayList<String> getSongsByTitle(String songTitle) {
        songTitle = songTitle.toLowerCase();
        ArrayList<String> songInfo = new ArrayList<String>();

        if (songsByTitle.containsKey(songTitle)) {
            for (Song song : songsByTitle.get(songTitle)) {
                songInfo.add(song.toString());
            }
        }

        return songInfo;
    }

    // returns an ArrayList of song info if found, empty ArrayList if not
    public ArrayList<String> getSongsByTitleFromStore(String songTitle) {
        songTitle = songTitle.toLowerCase();
        ArrayList<String> songInfo = new ArrayList<String>();

        ArrayList<Song> songs = store.getSongsByTitle(songTitle);
        for (Song song: songs) {
            songInfo.add(song.toString());
        }
        return songInfo;
    }

    // returns an ArrayList of song info if found, empty ArrayList if not
    public ArrayList<String> getSongsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        ArrayList<String> songInfo = new ArrayList<String>();

        if (songsByArtist.containsKey(artistName)) {
            for (Song song : songsByArtist.get(artistName)) {
                songInfo.add(song.toString());
            }
        }

        return songInfo;
    }

    // returns an ArrayList of song info if found, empty ArrayList if not
    public ArrayList<String> getSongsByArtistFromStore(String artistName) {
        artistName = artistName.toLowerCase();
        ArrayList<String> songInfo = new ArrayList<String>();

        ArrayList<Song> songs = store.getSongsByArtist(artistName);
        for (Song song: songs) {
            songInfo.add(song.toString());
        }

        return songInfo;
    }

    // returns an ArrayList of album info if found, empty ArrayList if not
    public ArrayList<String> getAlbumsByTitle(String albumTitle) {
        albumTitle = albumTitle.toLowerCase();
        ArrayList<String> albumInfo = new ArrayList<String>();

        if (albumsByTitle.containsKey(albumTitle)) {
            for (Album album : albumsByTitle.get(albumTitle)) {
                albumInfo.add(album.toString());
            }
        }

        return albumInfo;
    }

    // returns an ArrayList of album info if found, empty ArrayList if not
    public ArrayList<String> getAlbumsByTitleFromStore(String albumTitle) {
        albumTitle = albumTitle.toLowerCase();
        ArrayList<String> albumInfo = new ArrayList<String>();

        ArrayList<Album> albums = store.getAlbumsByTitle(albumTitle);
        for (Album album: albums) {
            albumInfo.add(album.toString());
        }

        return albumInfo;
    }

    // returns an ArrayList of album info if found, empty ArrayList if not
    public ArrayList<String> getAlbumsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        ArrayList<String> albumInfo = new ArrayList<String>();

        if (albumsByArtist.containsKey(artistName)) {
            for (Album album : albumsByArtist.get(artistName)) {
                albumInfo.add(album.toString());
            }
        }

        return albumInfo;
    }

    // returns an ArrayList of album info if found, empty ArrayList if not
    public ArrayList<String> getAlbumsByArtistFromStore(String artistName) {
        artistName = artistName.toLowerCase();
        ArrayList<String> albumInfo = new ArrayList<String>();

        ArrayList<Album> albums = store.getAlbumsByArtist(artistName);
        for (Album album: albums) {
            albumInfo.add(album.toString());
        }

        return albumInfo;
    }

    // search for a playlist by name,
    // returns a playlist represented by string if found, empty string if not
    public String getPlaylistByName(String playlistName) {
        playlistName = playlistName.toLowerCase();
        if (playlists.containsKey(playlistName)) {
            return playlists.get(playlistName).toString();
        } else {
            return "";
        }
    }


    public String getRating(String songName, String artistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();

        // first, check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(songName);
        if (potentialSongs.size() < 1) {
            return "Song not found.";
        } else {
            for (Song s : this.songsByTitle.get(songName)) {
                if (s.getArtist().equals(artistName)) {
                    // this means that s is the song we want to get the rating of.
                    if (s.getRating() == 0) {
                        return "No rating.";
                    }
                    return "" + s.getRating();
                }
            }
        }

        return "Could not find song rating";
    }


    /* "get a list of items from the library" - from the spec */
    // get a 'list' of song titles:
    public ArrayList<String> getSongTitles() {
        ArrayList<String> returnLst = new ArrayList<String>();
        for (String title : songsByTitle.keySet()) {
            returnLst.add(title);
        }
        return returnLst;
    }

    // get a 'list' of artists
    public ArrayList<String> getArtists() {
        ArrayList<String> returnLst = new ArrayList<String>();
        for (String artist : songsByArtist.keySet()) {
            returnLst.add(artist);
        }
        return returnLst;
    }

    // get a 'list' of albums names
    public ArrayList<String> getAlbums() {
        ArrayList<String> returnLst = new ArrayList<String>();
        for (String album : albumsByTitle.keySet()) {
            returnLst.add(album);
        }
        return returnLst;
    }

    // get a list of playlists
    public ArrayList<String> getPlaylists() {
        ArrayList<String> returnLst = new ArrayList<String>();
        for (String playlistName : playlists.keySet()) {
            returnLst.add(playlistName);
        }
        return returnLst;
    }

    // get a list of favorite songs
    public ArrayList<String> getFavorites() {
        ArrayList<String> returnLst = new ArrayList<String>();
        for (Song song : favorites) {
            returnLst.add(song.getTitle());
        }
        return returnLst;
    }

    /*=============================================================================================
     *
     * Methods
     *
     *===========================================================================================*/

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
            songsByArtist.get(artistName).add(new Song(found));
            if (!songsByTitle.containsKey(songName)) {
                songsByTitle.put(songName, new ArrayList<Song>());
            }
            songsByTitle.get(songName).add(new Song(found));
        }

        // returns true if found and false if null
        return (found != null);
    }

    public ArrayList<String> updateFrequentlyPlayedList(String songName, String artistName) {
        ArrayList<String> returnArr = new ArrayList<String>();
        ArrayList<Song> frequentlyPlayedListSubList = new ArrayList<Song>();
        boolean alreadyFound = false;
        Song mySong = null;
        for (Song s : this.songsByTitle.get(songName)) {
            if (s.getArtist().equals(artistName)) {
                // this means that s is the song we want.
                mySong = s;
            }
        }
        for (Song lists : this.frequentlyPlayedList) {
            if (lists.getArtist().equals(artistName) && lists.getTitle().equals(songName)) {
                // if the song is already in the list:
                alreadyFound = true;
            }
        }

        // if the sound is not already in the list, and the song is a valid object,
        // add it to the list.
        if (!alreadyFound && (mySong != null)) {
            this.frequentlyPlayedList.add(mySong);
        }

        // every time a song is played, the list must be re-sorted:
        Comparator songComparator = new CompareSongs();
        Collections.sort(this.frequentlyPlayedList, songComparator);

        // take the first 10 songs from the list, put into sublist
        // and if the entire list is <= 10 songs, just copy the list
        if (this.frequentlyPlayedList.size() <= 10) {
            frequentlyPlayedListSubList = this.frequentlyPlayedList;
        } else {
            // have to do this because .subList() does not return an ArrayList.
            List<Song> subListView = frequentlyPlayedList.subList(0, 10);
            // creates an ArrayList instance from the List instance above.
            frequentlyPlayedListSubList = new ArrayList<>(subListView);
        }

        // build ArrayList of strings to return:
        for (Song fSong : frequentlyPlayedListSubList) {
            String addStr = fSong.getTitle() + " by " + fSong.getArtist();
            returnArr.add(addStr);
        }

        return returnArr;
    }

    public boolean favoriteASong(String songName, String artistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();

        // first, check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(songName);
        if (potentialSongs.size() < 1) {
            return false;
        } else {
            for (Song s : this.songsByTitle.get(songName)) {
                if (s.getArtist().equals(artistName)) {
                    // this means that s is the song we want to favorite.
                    s.setFavorite();
                    if (!favorites.contains(s)){
                        favorites.add(s);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean playASong(String songName, String artistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();

        // first, check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(songName);
        if (potentialSongs.size() < 1) {
            return false;
        } else {
            for (Song s : this.songsByTitle.get(songName)) {
                if (s.getArtist().equals(artistName)) {
                    // this means that s is the song we want to play.
                    s.play();
                    return true;
                }
            }
        }
        return false;
    }

    // returns -1 if song not found
    public int getPlays(String songName, String artistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();

        // first, check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(songName);
        if (potentialSongs.size() < 1) {
            return -1;
        } else {
            for (Song s : this.songsByTitle.get(songName)) {
                if (s.getArtist().equals(artistName)) {
                    // this means that s is the song we want to get the plays of.
                    return s.getPlays();
                }
            }
        }
        return -1;
    }

    public boolean rateASong(String songName, String artistName, String rating) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();
        int actualRating = 0;

        try {
            actualRating = Integer.parseInt(rating);
        } catch (Exception e) {
            return false;
        }

        if (actualRating < 1 || actualRating > 5) {
            return false;
        }

        // check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(songName);
        if (potentialSongs.size() < 1) {
            return false;
        } else {
            for (Song s : this.songsByTitle.get(songName)) {
                // this means that s is the song we want to rate.
                if (s.getArtist().equals(artistName)) {
                    if (actualRating == 5) {
                        this.favoriteASong(songName, artistName);
                    }
                    s.setRating(actualRating);
                    return true;
                }
            }
        }

        return false;
    }


    // Add an Album from the store to the library
    public boolean addAlbumToLibrary (String albumName, String artistName) {
        albumName = albumName.toLowerCase();
        artistName = artistName.toLowerCase();

        // Find album in store
        ArrayList<Album> potentialAlbumList = store.getAlbumsByArtist(artistName);
        Album found = null;
        for (Album album : potentialAlbumList) {
            if (album.getTitle().equals(albumName)) {
                found = album;
            }
        }

        // Add album to appropriate hashmap
        if (found != null) {
            if (!albumsByArtist.containsKey(artistName)) {
                albumsByArtist.put(artistName, new ArrayList<Album>());
            }
            albumsByArtist.get(artistName).add(new Album(found));
            if (!albumsByTitle.containsKey(albumName)) {
                albumsByTitle.put(albumName, new ArrayList<Album>());
            }
            albumsByTitle.get(albumName).add(new Album(found));

            // add all songs to library from album
            for (Song song : found.getSongs()) { this.addSongToLibrary(song.getTitle(), song.getArtist()); }
        }

        // returns true if found and false if null
        return (found != null);
    }

    public void createPlaylist (String name) {
        if (this.playlists.containsKey(name)) {
            this.playlists.remove(name);
        }
        this.playlists.put(name, new Playlist(name.toLowerCase()));
    }

    // Add a song from library to playlist
    // Returns true if song is added, false otherwise
    public boolean addSongToPlaylist (String songName, String artistName, String playlistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();
        playlistName = playlistName.toLowerCase();

        // find playlist
        Playlist playlist = this.playlists.get(playlistName);
        if (playlist == null) {
            return false;
        }

        // find song
        Song found = null;
        ArrayList<Song> songs = this.songsByTitle.get(songName);
        if (songs == null) {
            return false;
        }
        for (Song song : songs) {
            if (song.getArtist().equals(artistName)) {
                found = song;
            }
        }

        if (found!=null) {
            playlist.addSong(found);
            return true;
        } else {
            return false;
        }
    }

    // Remove song from playlist, returns true if changes made, false otherwise
    public boolean removeSongFromPlaylist (String songName, String artistName, String playlistName) {
        songName = songName.toLowerCase();
        artistName = artistName.toLowerCase();
        playlistName = playlistName.toLowerCase();

        // find playlist
        Playlist playlist = this.playlists.get(playlistName);
        if (playlist == null) {
            return false;
        }
        // Iterate and remove song
        for (Song song : playlist.getSongs()) {
            if (song.getArtist().equals(artistName) && song.getTitle().equals(songName)) {
                playlist.removeSong(song);
                return true;
            }
        }
        return false;
    }


}





