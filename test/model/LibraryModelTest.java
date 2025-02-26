package test.model;

import org.junit.Assert;
import src.model.LibraryModel;
import org.junit.Test;
import src.store.MusicStore;

public class LibraryModelTest {
    MusicStore testStore = new MusicStore();
    LibraryModel testModel = new LibraryModel(testStore);

    @Test
    public void testAddToLibrary() {
        Assert.assertTrue(testModel.addSongToLibrary("Banjo", "Leonard Cohen"));
        Assert.assertEquals("banjo", testModel.getSongsByArtist("Leonard Cohen").get(0).getTitle());
    }
}
