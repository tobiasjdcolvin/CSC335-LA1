package src.model;

import src.store.Album;
import src.store.MusicStore;
import src.store.Song;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {
    // current user (for logging in)
    private LibraryModel currUser = null;
    private MusicStore musicStore;
    // keys: username, values: password
    private HashMap<String, String> userPasswords;

    private HashMap<String, String> userSalts;
    // keys: username, values: LibraryModel objects (basically all user info)
    private HashMap<String, LibraryModel> users;

    private ArrayList<String> recentlyPlayed;
    private ArrayList<String> frequentlyPlayed;

    public UserModel(MusicStore musicStore) {
        this.musicStore = musicStore;
        this.userPasswords = new HashMap<String, String>();
        this.userSalts = new HashMap<String, String>();
        this.users = new HashMap<String, LibraryModel>();
        this.recentlyPlayed = new ArrayList<String>();
        this.frequentlyPlayed = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            // For before the user has played 10 songs:
            recentlyPlayed.add("No song");
        }

        // populate userPasswords hashmap from UserStore file
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("src/store/UserStore.txt"));

            String line;
            // for every line in UserStore.txt:
            while ((line = bReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                // splitTxt[0] = username, splitTxt[1] password
                String[] splitTxt = line.split(" ");
                String username = splitTxt[0];
                String password = splitTxt[1];
                String salt = splitTxt[2];

                // populate the hashmaps
                if (!(this.userPasswords.containsKey(username))) {
                    this.userPasswords.put(username, password);
                    this.userSalts.put(username, salt);
                    LibraryModel newUserLibrary = new LibraryModel(musicStore);
                    this.users.put(username, newUserLibrary);
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading files");
        }
    }

    // call this when registering a new user
    public String addUser(String username, String password) {
        String salt = createSalt();
        if (!(this.userPasswords.containsKey(username))) {
            // add username, password to txt file, and update hashmaps

            // appending to the end of the file (that is what the second argument does)
            try(FileWriter myfilewriter = new FileWriter("src/store/UserStore.txt", true);
                BufferedWriter mybufferedwriter = new BufferedWriter(myfilewriter);
                // PrintWriter allows you to use familiar methods like print and println
                PrintWriter myprintwriter = new PrintWriter(mybufferedwriter))
            {
                String myString = username + " " + hash(password + salt) + " " + salt; // TODO: Add hashing here
                myprintwriter.print("\n");
                myprintwriter.print(myString);
            } catch (IOException exception) {
                // unable to add user due to file errors
                return "Internal server error";
            }

            // populate hashmaps
            this.userPasswords.put(username, hash(password + salt));
            LibraryModel newUserLibrary = new LibraryModel(musicStore);
            this.users.put(username, newUserLibrary);
            return "Successfully created account";
        } else {
            return "Username already exists";
        }
    }

    // Creates a random salt to be appended to the password during hashing
    private String createSalt() {
        String charSet = "qwertyuiopasdfghjklzxcvbnm,./;'[]1234567890-=QWERTYUIOPASDFGHJKLZXCVBNM<>?:{}|";
        Random random = new Random();
        String salt = "";
        for (int i = 0; i < 64; i++) {
            salt += charSet.charAt(random.nextInt(charSet.length()));
        }
        return salt;
    }

    // login as a registered user
    public String login(String username, String password) {
        if (userPasswords.containsKey(username)) {
            if (passwordCheck(username, password)) { // TODO: Add hashing here
                currUser = users.get(username);
                return "Successfully logged in as " + username;
            } else {
                return "Incorrect password, please try again";
            }
        } else {
            return "Account does not exist, please register a new account";
        }
    }

    private static String hash(String password) {
        try {
            StringBuilder hash = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); // Perform hash to get bytes
            for (byte b : bytes) {
                hash.append(Integer.toHexString(0xff & b)); // Convert bytes into hexadecimal strings (0xff is like a modulo)
            }
            return hash.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean passwordCheck(String username, String password) {
        //return password.equals(userPasswords.get(username));
        return hash(password + userSalts.get(username)).equals(userPasswords.get(username));
    }

    public void logout() {
        this.currUser = null;
    }

    public int getNumUsers() {
        return this.users.size();
    }

    public boolean getLoggedIn() {

        return this.currUser != null;
    }

    // methods from LibraryModel:
    public ArrayList<String> getSongsByTitleFromStore(String input) {
        return this.currUser.getSongsByTitleFromStore(input);
    }

    public ArrayList<String> getSongsByArtistFromStore(String input) {
        return this.currUser.getSongsByArtistFromStore(input);
    }

    public ArrayList<String> getAlbumsByTitleFromStore(String input) {
        return this.currUser.getAlbumsByTitleFromStore(input);
    }

    public ArrayList<String> getAlbumsByArtistFromStore(String input) {
        return this.currUser.getAlbumsByArtistFromStore(input);
    }

    public ArrayList<String> getSongsByTitle(String input) {
        return this.currUser.getSongsByTitle(input);
    }

    public ArrayList<String> getSongsByArtist(String input) {
        return this.currUser.getSongsByArtist(input);
    }

    public ArrayList<String> getAlbumsByTitle(String input) {
        return this.currUser.getAlbumsByTitle(input);
    }

    public ArrayList<String> getAlbumsByArtist(String input) {
        return this.currUser.getAlbumsByArtist(input);
    }

    public String getPlaylistByName(String input) {
        return this.currUser.getPlaylistByName(input);
    }

    public boolean addSongToLibrary(String input1, String input2) {
        return this.currUser.addSongToLibrary(input1, input2);
    }

    public boolean addAlbumToLibrary(String input1, String input2) {
        return this.currUser.addAlbumToLibrary(input1, input2);
    }

    public ArrayList<String> getSongTitles() {
        return this.currUser.getSongTitles();
    }

    public void createPlaylist(String input) {
        this.currUser.createPlaylist(input);
    }

    public boolean addSongToPlaylist(String input1, String input2, String input3) {
        return this.currUser.addSongToPlaylist(input1, input2, input3);
    }

    public boolean removeSongFromPlaylist(String input1, String input2, String input3) {
        return this.currUser.removeSongFromPlaylist(input1, input2, input3);
    }

    public ArrayList<String> getArtists() {
        return this.currUser.getArtists();
    }

    public ArrayList<String> getAlbums() {
        return this.currUser.getAlbums();
    }

    public ArrayList<String> getPlaylists() {
        return this.currUser.getPlaylists();
    }

    public ArrayList<String> getFavorites() {
        return this.currUser.getFavorites();
    }

    public boolean favoriteASong(String input1, String input2) {
        return this.currUser.favoriteASong(input1, input2);
    }

    public boolean rateASong(String input1, String input2, String input3) {
        return this.currUser.rateASong(input1, input2, input3);
    }

    public String getRating(String input1, String input2) {
        return this.currUser.getRating(input1, input2);
    }

    public boolean playASong(String songName, String artistName) {
        boolean result = this.currUser.playASong(songName, artistName);

        if (result == true) {
            recentlyPlayed.add(0, songName + " by " + artistName);
            recentlyPlayed.remove(10);
            this.updateFrequentlyPlayed(songName, artistName);
        }
        return result;
    }

    private void updateFrequentlyPlayed(String songName, String artistName) {
        this.frequentlyPlayed = currUser.updateFrequentlyPlayedList(songName, artistName);
    }

    public String getRecentlyPlayed() {
        String returnStr = "";
        for (String s : recentlyPlayed) {
            if (!(s.equals("No song"))) {
                returnStr += s + "\n";
            }
        }

        if (returnStr.equals("")) {
            return "No songs played yet";
        }
        return returnStr.stripTrailing();
    }

    public String getFrequentlyPlayed() {
        String returnStr = "";
        for (String s : this.frequentlyPlayed) {
            returnStr += s + "\n";
        }

        if (returnStr.equals("")) {
            return "No songs played yet";
        }
        return returnStr.stripTrailing();
    }

    public int getPlays(String songName, String artistName) {
        return this.currUser.getPlays(songName, artistName);
    }
}
