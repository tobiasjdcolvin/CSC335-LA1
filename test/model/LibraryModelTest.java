package test.model;

import org.junit.Assert;
import src.model.LibraryModel;
import org.junit.Test;
import src.store.MusicStore;
import src.store.Song;

import java.util.ArrayList;

public class LibraryModelTest {
    MusicStore testStore = new MusicStore();
    LibraryModel testModel = new LibraryModel(testStore);

    @Test
    public void testAddToLibrary() {
        Assert.assertTrue(testModel.addSongToLibrary("Banjo", "Leonard Cohen"));
        Assert.assertEquals(1, testModel.getSongsByTitle("Banjo").size());
    }

    @Test
    public void testGetSongTitles() {
        testModel.addSongToLibrary("Tired", "Adele");
        testModel.addSongToLibrary("Secrets", "OneRepublic");
        Assert.assertEquals(2, testModel.getSongTitles().size());
    }

    @Test
    public void testGetSongsByTitle() {
        testModel.addSongToLibrary("Tired", "Adele");
        ArrayList<String> songList = testModel.getSongsByTitle("Tired");
        Assert.assertTrue(songList.size() == 1);
    }

    @Test
    public void testGetAlbumsByTitle() {
        ArrayList<String> albumList = testModel.getAlbumsByTitle("19");
        Assert.assertEquals(0, albumList.size());
    }

    @Test
    public void testGetSongsByArtist() {
        testModel.addSongToLibrary("Secrets", "OneRepublic");
        ArrayList<String> songList = testModel.getSongsByArtist("OneRepublic");
        Assert.assertEquals(1, songList.size());
    }

    @Test
    public void testGetSongsByTitleFromStore() {
        ArrayList<String> songList = testModel.getSongsByTitleFromStore("Secrets");
        Assert.assertEquals(1, songList.size());
    }

    @Test
    public void testGetAlbumsByTitleFromStore() {
        ArrayList<String> albumList = testModel.getAlbumsByTitleFromStore("19");
        Assert.assertEquals(1, albumList.size());
    }

    @Test
    public void testGetSongsByArtistFromStore() {
        ArrayList<String> songList = testModel.getSongsByArtistFromStore("Adele");
        Assert.assertEquals(24, songList.size());
    }

    @Test
    public void testGetAlbumsByArtistFromStore() {
        ArrayList<String> albumList = testModel.getAlbumsByArtistFromStore("Adele");
        Assert.assertEquals(2, albumList.size());
    }
}
