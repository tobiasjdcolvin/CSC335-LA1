package src.model;

import src.store.Album;
import src.store.MusicStore;
import src.store.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {
    // current user (for logging in)
    private LibraryModel currUser = null;

    private MusicStore musicStore;
    // keys: username, values: password
    private HashMap<String, String> userPasswords;
    // keys: username, values: LibraryModel objects (basically all user info)
    private HashMap<String, LibraryModel> users;

    public UserModel(MusicStore musicStore) {
        this.musicStore = musicStore;
        this.userPasswords = new HashMap<String, String>();
        this.users = new HashMap<String, LibraryModel>();

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

                // populate the hashmaps
                if (!(this.userPasswords.containsKey(username))) {
                    this.userPasswords.put(username, password);
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
        if (!(this.userPasswords.containsKey(username))) {
            // add username, password to txt file, and update hashmaps
            try(FileWriter myfw = new FileWriter("src/store/UserStore.txt", true);
                BufferedWriter mybw = new BufferedWriter(myfw);
                PrintWriter myout = new PrintWriter(mybw))
            {
                String myString = username + " " + password;
                myout.print("\n");
                myout.print(myString);
            } catch (IOException exception) {
                // unable to add user due to file errors
                return "Internal server error";
            }

            // populate hashmaps
            this.userPasswords.put(username, password);
            LibraryModel newUserLibrary = new LibraryModel(musicStore);
            this.users.put(username, newUserLibrary);
            return "Succesfully created account";
        } else {
            return "Username already exists";
        }
    }

    // login as a registered user
    public String login(String username, String password) {
        if (userPasswords.containsKey(username)) {
            if (userPasswords.get(username).equals(password)) {
                currUser = users.get(username);
                return "Successfully logged in as " + username;
            } else {
                return "Incorrect password, please try again";
            }
        } else {
            return "Account does not exist, please register a new account";
        }
    }

    public int getNumUsers() {
        return this.users.size();
    }

    // for debugging
    public boolean getCurrUserIsNull() {
        return this.currUser == null;
    }
}
