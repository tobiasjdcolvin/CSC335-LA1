package src;

import src.model.LibraryModel;
import src.model.UserModel;
import src.store.MusicStore;
import src.view.View;

public class Main {
    public static void main(String[] args) {
        MusicStore store = new MusicStore();
        UserModel userModel = new UserModel(store);
        // How it will be: View.start(userModel);

        // temporary (just so everything builds right now):
        LibraryModel tempLibaryModel = new LibraryModel(store);
        View.start(tempLibaryModel);
    }
}
