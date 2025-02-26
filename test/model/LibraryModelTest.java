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
        // TODO: actually write real tests instead of just printing stuff
        for (String title : testModel.getSongTitles()) {
            System.out.println(title);
        }
    }

    @Test
    public void testGetSongsByTitle() {
        testModel.addSongToLibrary("Tired", "Adele");

        ArrayList<String> songList = testModel.getSongsByTitle("Tired");
        // TODO: actually write real tests instead of just printing stuff
        for (String s : songList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetAlbumsByTitle() {
        // TODO: CHECK THIS UNIT TEST AFTER IMPLEMENTING ADDALBUMTOLIBRARY
        ArrayList<String> albumList = testModel.getAlbumsByTitle("19");
        // TODO: actually write real tests instead of just printing stuff
        for (String a : albumList) {
            System.out.println(a);
        }
    }

    @Test
    public void testGetSongsByArtist() {
        testModel.addSongToLibrary("Secrets", "OneRepublic");

        ArrayList<String> songList = testModel.getSongsByArtist("OneRepublic");
        // TODO: actually write real tests instead of just printing stuff
        for (String s : songList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetSongsByTitleFromStore() {
        ArrayList<String> songList = testModel.getSongsByTitleFromStore("Secrets");

        // TODO: actually write real tests instead of just printing stuff
        for (String s : songList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetAlbumsByTitleFromStore() {
        ArrayList<String> albumList = testModel.getAlbumsByTitleFromStore("19");

        // TODO: actually write real tests instead of just printing stuff
        for (String a : albumList) {
            System.out.println(a);
        }
    }

    @Test
    public void testGetSongsByArtistFromStore() {
        ArrayList<String> songList = testModel.getSongsByArtistFromStore("Adele");

        // TODO: actually write real tests instead of just printing stuff
        for (String s : songList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetAlbumsByArtistFromStore() {
        ArrayList<String> albumList = testModel.getAlbumsByArtistFromStore("Adele");

        // TODO: actually write real tests instead of just printing stuff
        for (String a : albumList) {
            System.out.println(a + "\n");
        }
    }
}
