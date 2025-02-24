package src.store;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class MusicStore {
    private HashMap<String, Song> songsByTitle;
    // must have a nested ArrayList, since an artist can have multiple songs
    private HashMap<String, ArrayList<Song>> songsByArtist;

    private ArrayList<String> albumFiles;

    // constructor
    public MusicStore() {
        this.songsByTitle = new HashMap<String, Song>();
        this.songsByArtist = new HashMap<String, ArrayList<Song>>();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader("src/albums/albums.txt"));

            // construct the file names for all the album files, and
            // store them as strings in an ArrayList
            this.albumFiles = new ArrayList<String>();
            String line;
            if (bReader.ready()){
                // for every line in albums.txt, split the line on
                // the comma, and construct the file names accordingly,
                // then add each file name to albumFiles
                while ((line = bReader.readLine()) != null) {
                    String[] splitTxt = line.split(",");
                    // format: "src/albums/{ALBUM NAME}.txt"
                    String fileName = "src/albums/" + splitTxt[0] + "_" + splitTxt[1] + ".txt";
                    albumFiles.add(fileName);
                }
            }

            // populate the hashmaps:
            for (String album : this.albumFiles) {
                bReader = new BufferedReader(new FileReader(album));
                // for the first line, split it on commas to extract album name,
                // artist name.
                String currLine;
                if (bReader.ready()) {
                    currLine = bReader.readLine().toLowerCase(); // set everything to lowercase for consistency
                    String[] splitLine = currLine.split(",");
                    String currAlbumTitle = splitLine[0];
                    String currArtistName = splitLine[1];

                    while ((currLine = bReader.readLine()) != null) {
                        currLine = currLine.toLowerCase();
                        // each of these lines are songs, so for each song:
                        // first create a new song object for each song
                        // note: currLine is the title of the current song
                        Song currSong = new Song(currLine, currArtistName, currAlbumTitle);
                        // populate songsByTitle (song titles as keys, Song objs as vals):
                        this.songsByTitle.put(currLine, currSong);
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

    // returns a Song object if found, null if not
    public Song getSongByTitle(String songTitle) {
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
}
