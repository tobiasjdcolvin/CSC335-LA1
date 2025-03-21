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
    private ArrayList<String> songTitlesList;
    private HashMap<String, ArrayList<Album>> albumsByArtist;
    private ArrayList<Song> frequentlyPlayedList;

    // playlists:
    private HashMap<String, Playlist> playlists;
    // list of favorite songs:
    private ArrayList<Song> favorites;

    // Used to keep track of songs by genre
    private final HashMap<String, Playlist> genreTracker;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    public LibraryModel(MusicStore store) {
        this.store = store;

        this.songsByTitle = new HashMap<String, ArrayList<Song>>();
        this.songTitlesList = new ArrayList<String>();
        this.songsByArtist = new HashMap<String, ArrayList<Song>>();
        this.albumsByTitle = new HashMap<String, ArrayList<Album>>();
        this.albumsByArtist = new HashMap<String, ArrayList<Album>>();
        this.genreTracker = new HashMap<String, Playlist>();

        this.playlists = new HashMap<String, Playlist>();

        this.favorites = new ArrayList<Song>();

        this.frequentlyPlayedList = new ArrayList<Song>();

        this.createPlaylist("recently played");
        this.createPlaylist("frequently played");
        this.createPlaylist("favorite songs");
        this.createPlaylist("top rated songs");
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
            return -1*(s1.getRating() - s2.getRating());
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
        this.updateSongTitlesList();
        return this.songTitlesList;
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

        // Add song to playlist
        Album foundAlbum = null;
        if (found != null) {
            ArrayList<Album> albums = store.getAlbumsByTitle(found.getAlbum());

            for (Album album : albums) {
                if (album.getArtist().equals(found.getArtist())) {
                    foundAlbum = album;
                    break;
                }
            }
        }
        if (foundAlbum != null) {
            if (!this.genreTracker.containsKey("genre:"+foundAlbum.getGenre())) {
                this.genreTracker.put("genre:"+foundAlbum.getGenre(), new Playlist("genre:"+foundAlbum.getGenre()));
            }
            this.genreTracker.get("genre:"+foundAlbum.getGenre()).addSong(found);
            if (this.genreTracker.get("genre:"+foundAlbum.getGenre()).getSongs().size() >= 10) {
                this.playlists.put("genre:"+foundAlbum.getGenre(), this.genreTracker.get("genre:"+foundAlbum.getGenre()));
            }
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

        // find playlist
        Playlist playlist = this.playlists.get(playlistName);
        if (playlist == null) {
            return false;
        }
        Song toRemoveS = null;
        // Iterate and remove song
        for (Song song : playlist.getSongs()) {
            if (song.getArtist().equals(artistName) && song.getTitle().equals(songName)) {
                toRemoveS = song;
            }
        }
        if (toRemoveS != null) {
            playlist.removeSong(toRemoveS);
            return true;
        }
        return false;
    }

    public String removeSong(String title, String artist) {
        title = title.toLowerCase();
        artist = artist.toLowerCase();

        // for songsByTitle:
        if (songsByTitle.containsKey(title)) {
            // if there is only one song with the title, remove the whole key-value pair,
            // if the song has the matching artist as expected
            if (songsByTitle.get(title).size() == 1) {
                if (songsByTitle.get(title).get(0).getArtist().toLowerCase().equals(artist)) {
                    songsByTitle.remove(title);
                } else {
                    return "Unable to remove song";
                }
                // if there is more than one song of the same title, handle that:
            } else {
                // check all songs to find the one with the matching artist to remove
                Song toRemoveS = null;
                for (Song s : songsByTitle.get(title)) {
                    if (s.getArtist().toLowerCase().equals(artist)) {
                        toRemoveS = s;
                    }
                }
                if (toRemoveS != null) {
                    songsByTitle.get(title).remove(toRemoveS);
                } else {
                    return "Unable to remove song";
                }
            }
        }

        // for songsByArtist:
        if (songsByArtist.containsKey(artist)) {
            if (songsByArtist.get(artist).size() == 1) {
                // if the artist has 1 song, remove the entire key-value pair if the song is the
                // expected one to remove.
                if (songsByArtist.get(artist).get(0).getTitle().equals(title)) {
                    songsByArtist.remove(artist);
                } else {
                    return "Unable to remove song";
                }
            } else {
                Song toRemoveS = null;
                for (Song s : songsByArtist.get(artist)) {
                    if (s.getTitle().equals(title)) {
                        toRemoveS = s;
                    }
                }

                if (toRemoveS != null) {
                    // remove the song if found
                    songsByArtist.get(artist).remove(toRemoveS);
                } else {
                    return "Unable to remove song";
                }
            }
        }

        // for frequentlyPlayedList:
        if (frequentlyPlayedList.size() > 0) {
            Song foundS = null;
            for (Song s: frequentlyPlayedList) {
                if (s.getTitle().toLowerCase().equals(title) && s.getArtist().toLowerCase().equals(artist)) {
                    foundS = s;
                }
            }
            if (foundS != null) {
                frequentlyPlayedList.remove(foundS);
            }
        }

        // for each playlist
        for (String pName : playlists.keySet()) {
            removeSongFromPlaylist(title, artist, pName);
        }

        // for favorites:
        if (favorites.size() > 0) {
            Song foundS = null;
            for (Song s: favorites) {
                if (s.getTitle().toLowerCase().equals(title) && s.getArtist().toLowerCase().equals(artist)) {
                    foundS = s;
                }
            }
            if (foundS != null) {
                favorites.remove(foundS);
            }
        }

        // for each album, remove the song if it exists in the album:
        for (ArrayList<Album> alList : this.albumsByTitle.values()) {
            for (Album al : alList) {
                al.removeSong(title, artist);
            }
        }

        return "Successfully removed " + title + " by " + artist;
    }

    public String removeAlbum(String name, String artist) {
        ArrayList<Song> songsToRemove = new ArrayList<Song>();
        name = name.toLowerCase();
        artist = artist.toLowerCase();

        // for albumsByTitle:
        if (albumsByTitle.containsKey(name)) {
            // store all the songs in the album before deleting the album,
            // so the songs can individually be deleted later.
            for (Album al : albumsByTitle.get(name)) {
                if (al.getArtist().toLowerCase().equals(artist)) {
                    // store each of the songs:
                    for (Song s : al.getSongs()) {
                        songsToRemove.add(s);
                    }
                }
            }

            // if there is only one album with the title, remove the whole key-value pair,
            // if the album has the matching artist as expected
            if (albumsByTitle.get(name).size() == 1) {
                if (albumsByTitle.get(name).get(0).getArtist().toLowerCase().equals(artist)) {
                    albumsByTitle.remove(name);
                } else {
                    return "Unable to remove album";
                }
                // if there is more than one album of the same title, handle that:
            } else {
                // check all albums to find the one with the matching artist to remove
                Album toRemoveA = null;
                for (Album a : albumsByTitle.get(name)) {
                    if (a.getArtist().toLowerCase().equals(artist)) {
                        toRemoveA = a;
                    }
                }
                if (toRemoveA != null) {
                    albumsByTitle.get(name).remove(toRemoveA);
                } else {
                    return "Unable to remove album";
                }
            }
        }

        // for albumsByArtist:
        if (albumsByArtist.containsKey(artist)) {
            if (albumsByArtist.get(artist).size() == 1) {
                // if the artist has 1 album, remove the entire key-value pair if the album is the
                // expected one to remove.
                if (albumsByArtist.get(artist).get(0).getTitle().equals(name)) {
                    albumsByArtist.remove(artist);
                } else {
                    return "Unable to remove album";
                }
            } else {
                Album toRemoveA = null;
                for (Album a : albumsByArtist.get(artist)) {
                    if (a.getTitle().equals(name)) {
                        toRemoveA = a;
                    }
                }

                if (toRemoveA != null) {
                    // remove the album if found
                    albumsByArtist.get(artist).remove(toRemoveA);
                } else {
                    return "Unable to remove album";
                }
            }
        }

        // remove each of the songs that were in the album:
        for (Song s : songsToRemove) {
            this.removeSong(s.getTitle().toLowerCase(), s.getArtist().toLowerCase());
        }

        return "Successfully removed " + name + " by " + artist;
    }

    public String shuffleLibrary() {
        for (String title : songsByTitle.keySet()) {
            if (!(this.songTitlesList.contains(title))) {
                this.songTitlesList.add(title);
            }
        }
        Collections.shuffle(songTitlesList);
        Collections.shuffle(songTitlesList);
        return "Successfully shuffled library";
    }

    private void updateSongTitlesList() {
        for (String title : songsByTitle.keySet()) {
            if (!(this.songTitlesList.contains(title))) {
                this.songTitlesList.add(title);
            }
        }
    }

    public String shufflePlaylist(String playlistName) {
        if (this.playlists.containsKey(playlistName)) {
            this.playlists.get(playlistName).shuffle();
            return "Successfully shuffled playlist: " + playlistName;
        } else {
            return "Unable to find playlist: " + playlistName;
        }
    }

    public String albumOfSong(String title, String artist) {
        title = title.toLowerCase();
        artist = artist.toLowerCase();

        // check if the song exists in the library:
        ArrayList<String> potentialSongs = this.getSongsByTitle(title);
        if (potentialSongs.size() < 1) {
            return "";
        } else {
            for (Song s : this.songsByTitle.get(title)) {
                // this means that s is the song we want.
                if (s.getArtist().equals(artist)) {
                    // find the exact album
                    for (Album al : this.albumsByTitle.get(s.getAlbum())) {
                        if (al.getArtist().equals(artist)) {
                            // this means that al is the album we want.
                            return al.toString() + "\n\nThis album is in your library.";
                        }
                    }
                }
            }
        }
        return "";
    }
}
