package src.view;

import java.util.ArrayList;
import java.util.Scanner;
import src.model.LibraryModel;
import src.store.MusicStore;

public class View {
    public static void main(String[] args) {
        MusicStore store = new MusicStore();
        LibraryModel model = new LibraryModel(store);
        Scanner scanner = new Scanner(System.in);
        // add any new commands here and give descriptions of them:
        String commandsInfo = """
                'exit': exits the program
                'clear': clears the screen
                --------------------------------------------------------------
                MUSIC STORE COMMANDS:
                'store songs title': search the store for a song by title
                'store songs artist': search the store for songs by an artist
                'store albums title': search the store for an album by title
                'store albums artist': search the store for albums by an artist
                --------------------------------------------------------------
                LIBRARY COMMANDS:
                'my songs title': search your library for a song by title
                'my songs artist': search your library for songs by an artist
                'my albums title': search your library for an album by title
                'my albums artist': search your library for albums by an artist
                'find playlist': search your playlists for a playlist by name
                'add song': add a song to your library (as long as it is in the store)
                'add album': add an album to your library (as long as it is in the store)
                'get my songs': get a list of all song titles from your library
                'get my artists': get a list of all artists from your library
                'get my albums': get a list of all albums from your library
                'get my playlists': get a list of all playlists from your library
                'get my favorites': get a list of all favorite songs from your library
                """;

        String userInput = "";
        System.out.println("\nType 'exit' to exit, 'help' for a list of commands\n");
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("exit") || userInput.equals("Exit") || userInput.equals("quit") || userInput.equals("Quit")) {
                break;
            } else if (userInput.equals("help") || userInput.equals("Help")) {
                System.out.println(commandsInfo);
                // I just wanted to have the ability to clear clutter from the screen
            }else if (userInput.equals("clear")) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                // use the following as a template for handling commands:
            }else if (userInput.equals("store songs title")) {
                System.out.println("Enter the title of the song you want to search for:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getSongsByTitleFromStore(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("Song not found.");
                }
            }else if (userInput.equals("store songs artist")) {
                System.out.println("Enter the artist's name you want to search for songs of:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getSongsByArtistFromStore(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("Artist not found.");
                }
            }else if (userInput.equals("store albums title")) {
                System.out.println("Enter the name of the album you want to search for:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getAlbumsByTitleFromStore(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info + "\n");
                    }
                } else {
                    System.out.println("Album not found.");
                }
            }else if (userInput.equals("store albums artist")) {
                System.out.println("Enter the name of the artist you want to search for albums of:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getAlbumsByArtistFromStore(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info + "\n");
                    }
                } else {
                    System.out.println("Artist not found.");
                }
            }else if (userInput.equals("my songs title")) {
                System.out.println("Enter the title of the song you want to search for:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getSongsByTitle(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("Song not found.");
                }
            }else if (userInput.equals("my songs artist")) {
                System.out.println("Enter the artist's name you want to search for songs of:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getSongsByArtist(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("Artist not found.");
                }
            }else if (userInput.equals("my albums title")) {
                System.out.println("Enter the name of the album you want to search for:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getAlbumsByTitle(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info + "\n");
                    }
                } else {
                    System.out.println("Album not found.");
                }
            }else if (userInput.equals("my albums artist")) {
                System.out.println("Enter the name of the artist you want to search for albums of:");
                String userResponse = scanner.nextLine();

                ArrayList<String> result = model.getAlbumsByArtist(userResponse);
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info + "\n");
                    }
                } else {
                    System.out.println("Artist not found.");
                }
            }else if (userInput.equals("find playlist")) {
                System.out.println("Enter the name of the playlist you want to search for:");
                String userResponse = scanner.nextLine();

                String result = model.getPlaylistByName(userResponse);
                if (!result.equals("")) {
                    System.out.println(result);
                } else {
                    System.out.println("No playlist found with that name.");
                }
            }else if (userInput.equals("add song")) {
                System.out.println("Enter the title of the song you want to add:");
                String userResponse1 = scanner.nextLine();
                System.out.println("Enter the name of the artist of the song you want to add:");
                String userResponse2 = scanner.nextLine();

                boolean result = model.addSongToLibrary(userResponse1, userResponse2);
                if (result == true) {
                    System.out.println("Successfully added " + userResponse1 + " by " + userResponse2 + ".");
                } else {
                    System.out.println("Unable to find that song in the music store.");
                }
            }else if (userInput.equals("add album")) {
                System.out.println("Enter the title of the album you want to add:");
                String userResponse1 = scanner.nextLine();
                System.out.println("Enter the name of the artist of the album you want to add:");
                String userResponse2 = scanner.nextLine();

                boolean result = model.addAlbumToLibrary(userResponse1, userResponse2);
                if (result == true) {
                    System.out.println("Successfully added " + userResponse1 + " by " + userResponse2 + ".");
                } else {
                    System.out.println("Unable to find that album in the music store.");
                }
            }else if (userInput.equals("get my songs")) {
                ArrayList<String> result = model.getSongTitles();
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No songs in library.");
                }
            }else if (userInput.equals("get my artists")) {
                ArrayList<String> result = model.getArtists();
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No artists in library.");
                }
            }else if (userInput.equals("get my albums")) {
                ArrayList<String> result = model.getAlbums();
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No albums in library.");
                }
            }else if (userInput.equals("get my playlists")) {
                ArrayList<String> result = model.getPlaylists();
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No playlists in library.");
                }
            }else if (userInput.equals("get my favorites")) {
                ArrayList<String> result = model.getFavorites();
                if (result.size() > 0) {
                    for (String info : result) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No favorites in library.");
                }
            }else {
                System.out.println("Command not recognised, please try again.");
            }
        }
    }
}
