package src.store;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class MusicStore {
    // TODO: I'm thinking that we should have a Song class, and
    // seperate hash maps for songs (keys being song titles),
    // songs (keys being artist names), albums (keys being album titles),
    // albums (keys being artist names). Lmk what you think.

    // constructor
    public MusicStore() {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("src/albums/albums.txt"));

            // construct the file names for all the album files, and
            // store them as strings in an ArrayList
            ArrayList<String> albumFiles = new ArrayList<String>();
            String line;
            if (bReader.ready()){
                // for every line in albums.txt, split the line on
                // the comma, and construct the file names accordingly,
                // then add each file name to albumFiles
                while ((line = bReader.readLine()) != null) {
                    String[] splitTxt = line.split(",");
                    String fileName = splitTxt[0] + "_" + splitTxt[1] + ".txt";
                    albumFiles.add(fileName);
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading files");
        }
    }
}
