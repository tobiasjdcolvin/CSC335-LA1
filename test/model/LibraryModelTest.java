package test.model;

import src.model.LibraryModel;
import org.junit.Test;
import src.store.MusicStore;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryModelTest {
    @Test
    public void testAddToLibrary() {
        LibraryModel l = new LibraryModel(new MusicStore());
        assertTrue(l.addSongToLibrary("Africa", "Toto"));
    }
}
