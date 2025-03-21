package src.store;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class MusicStore {
    // We chose to utilize hashmaps for storing the data, because
    // access via keys should be O(1).
    private final HashMap<String, ArrayList<Song>> songsByTitle;
    private final HashMap<String, ArrayList<Song>> songsByArtist;
    private final HashMap<String, ArrayList<Album>> albumsByTitle;
    private final HashMap<String, ArrayList<Album>> albumsByArtist;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    public MusicStore() {
        // Initialize Hashmaps
        this.songsByTitle = new HashMap<String, ArrayList<Song>>();
        this.songsByArtist = new HashMap<String, ArrayList<Song>>();
        this.albumsByTitle = new HashMap<String, ArrayList<Album>>();
        this.albumsByArtist = new HashMap<String, ArrayList<Album>>();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader("src/albums/albums.txt"));

            // construct the file names for all the album files, and
            // store them as strings in an ArrayList
            ArrayList<String> albumFiles = new ArrayList<String>();
            String line;

            // for every line in albums.txt, split the line on
            // the comma, and construct the file names accordingly,
            // then add each file name to albumFiles
            while ((line = bReader.readLine()) != null) {
                String[] splitTxt = line.split(",");
                // format: "src/albums/{ALBUM NAME}.txt"
                String fileName = "src/albums/" + splitTxt[0] + "_" + splitTxt[1] + ".txt";
                albumFiles.add(fileName);
            }

            // populate the hashmaps:
            for (String album : albumFiles) {
                bReader = new BufferedReader(new FileReader(album));
                // for the first line, split it on commas to extract album name,
                // artist name.
                String currLine;
                if (bReader.ready()) {
                    currLine = bReader.readLine().toLowerCase(); // set everything to lowercase for consistency
                    String[] splitLine = currLine.split(",");
                    String currAlbumTitle = splitLine[0];
                    String currArtistName = splitLine[1];
                    String currGenreName = splitLine[2];

                    // create an Album object to then use for the album related hashmaps
                    Album currAlbum = new Album(currAlbumTitle, currArtistName, currGenreName);

                    // put initial info into album hashmaps
                    if (!this.albumsByTitle.containsKey(currAlbumTitle)) {
                        this.albumsByTitle.put(currAlbumTitle, new ArrayList<Album>());
                    }
                    if (!this.albumsByArtist.containsKey(currArtistName)) {
                        this.albumsByArtist.put(currArtistName, new ArrayList<Album>());
                    }
                    this.albumsByTitle.get(currAlbumTitle).add(currAlbum);
                    this.albumsByArtist.get(currArtistName).add(currAlbum);

                    while ((currLine = bReader.readLine()) != null) {
                        currLine = currLine.toLowerCase();
                        // each of these lines are songs, so for each song:
                        // first create a new song object for each song
                        // note: currLine is the title of the current song
                        Song currSong = new Song(currLine, currArtistName, currAlbumTitle);
                        // add the current song to the album object
                        currAlbum.addSong(currSong);
                        // populate songsByTitle (song titles as keys, ArrayLists of Song objs as vals):
                        if (!this.songsByTitle.containsKey(currLine)) {
                            this.songsByTitle.put(currLine, new ArrayList<Song>());
                        }
                        this.songsByTitle.get(currLine).add(currSong);
                        // check whether the artist already exists in the
                        // songsByArtist hashmap,
                        // if they do, append to the list of their songs and if not,
                        // instantiate a new ArrayList and append to it the song
                        if (!this.songsByArtist.containsKey(currArtistName)) {
                            this.songsByArtist.put(currArtistName, new ArrayList<Song>());
                        }
                        this.songsByArtist.get(currArtistName).add(currSong);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading files");
        }
    }

    /*=============================================================================================
     *
     * Getter Methods
     *
     *===========================================================================================*/

    // returns an ArrayList of Song objects if found, empty ArrayList if not
    public ArrayList<Song> getSongsByTitle(String songTitle) {
        songTitle = songTitle.toLowerCase();
        if (songsByTitle.containsKey(songTitle)) {
            return songsByTitle.get(songTitle);
        } else {
            return new ArrayList<>();
        }
    }


    // returns an ArrayList of Song objects if found, empty ArrayList if not
    public ArrayList<Song> getSongsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        if (songsByArtist.containsKey(artistName)) {
            return songsByArtist.get(artistName);
        } else {
            return new ArrayList<>();
        }
    }


    // returns an ArrayList of Album objects if found, empty ArrayList if not
    public ArrayList<Album> getAlbumsByTitle(String albumTitle) {
        albumTitle = albumTitle.toLowerCase();
        if (albumsByTitle.containsKey(albumTitle)) {
            return albumsByTitle.get(albumTitle);
        } else {
            return new ArrayList<>();
        }
    }


    // returns an ArrayList of Album objects if found, empty ArrayList if not
    public ArrayList<Album> getAlbumsByArtist(String artistName) {
        artistName = artistName.toLowerCase();
        if (albumsByArtist.containsKey(artistName)) {
            return albumsByArtist.get(artistName);
        } else {
            return new ArrayList<>();
        }
    }
}