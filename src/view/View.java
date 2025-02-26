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
                'store songs title': search the store for a song by title
                'store songs artist': search the store for songs by an artist
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
            }else {
                System.out.println("Command not recognised, please try again.");
            }
        }
    }
}
