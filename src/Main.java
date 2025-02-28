package src;

import src.model.LibraryModel;
import src.store.MusicStore;
import src.view.View;

public class Main {
    public static void main(String[] args) {
        MusicStore store = new MusicStore();
        LibraryModel model = new LibraryModel(store);
        View.start(model);
    }
}
