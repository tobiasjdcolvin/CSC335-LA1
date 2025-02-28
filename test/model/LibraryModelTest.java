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
    public void testGetPlaylistByName() {
        testModel.addAlbumToLibrary("19", "adele");
        testModel.createPlaylist("taco");
        String result = testModel.getPlaylistByName("taco");
        Assert.assertTrue(!result.equals(""));
        String result2 = testModel.getPlaylistByName("tortilla");
        Assert.assertTrue(result2.equals(""));
    }

    @Test
    public void testFavoriteASong() {
        testModel.addAlbumToLibrary("19", "adele");
        testModel.favoriteASong("tired", "adele");
        ArrayList<String> result = testModel.getFavorites();
        Assert.assertEquals(1, result.size());
        testModel.favoriteASong("not a song", "not an artist");
        result = testModel.getFavorites();
        Assert.assertEquals(1, result.size());
        testModel.favoriteASong("tired", "not an artist");
        result = testModel.getFavorites();
        Assert.assertEquals(1, result.size());
        testModel.favoriteASong("tired", "adele");
        result = testModel.getFavorites();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testGetAlbumsByTitleIfExists() {
        testModel.addAlbumToLibrary("19", "adele");
        ArrayList<String> results = testModel.getAlbumsByTitle("19");
        Assert.assertEquals(1, results.size());

    }

    @Test
    public void testGetAlbumsByArtist() {
        testModel.addAlbumToLibrary("19", "adele");
        ArrayList<String> results = testModel.getAlbumsByArtist("adele");
        Assert.assertEquals(1, results.size());
        ArrayList<String> results2 = testModel.getAlbumsByArtist("bob");
        Assert.assertEquals(0, results2.size());
    }

    @Test
    public void testGetAlbumsByTitle() {
        ArrayList<String> albumList = testModel.getAlbumsByTitle("19");
        Assert.assertEquals(0, albumList.size());
    }

    @Test
    public void testRemoveSongFromPlaylist() {
        testModel.addAlbumToLibrary("19", "adele");
        testModel.createPlaylist("bog of dreams");
        testModel.addSongToPlaylist("tired", "adele", "bog of dreams");
        ArrayList<String> result = testModel.getPlaylists();
        Assert.assertEquals(1, result.size());
        testModel.removeSongFromPlaylist("tired", "adele", "bog of dreams");
        testModel.removeSongFromPlaylist("tired", "adele", "not a playlist");
        testModel.removeSongFromPlaylist("tired", "poopdog", "bog of dreams");
        testModel.removeSongFromPlaylist("blah", "adele", "bog of dreams");
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
    public void testGetRating() {
        testModel.addAlbumToLibrary("19", "adele");
        String result1 = testModel.getRating("tired", "adele");
        Assert.assertTrue(result1.equals("No rating."));
        testModel.rateASong("tired", "adele", "599999");
        String result2 = testModel.getRating("tired", "adele");
        Assert.assertTrue(result2.equals("No rating."));
        testModel.rateASong("tired", "adele", "5");
        String result3 = testModel.getRating("tired", "adele");
        Assert.assertFalse(result3.equals("No rating."));
        String result4 = testModel.getRating("fjdasl", "fjsklf");
        Assert.assertTrue(result4.equals("Song not found."));
        String result5 = testModel.getRating("tired", "fjsklf");
        Assert.assertFalse(result5.equals("Song not found."));
    }

    @Test
    public void testGetPlaylists() {
        testModel.addAlbumToLibrary("19", "adele");
        testModel.createPlaylist("bog of dreams");
        testModel.addSongToPlaylist("tired", "adele", "bog of dreams");
        ArrayList<String> result = testModel.getPlaylists();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testRateASongEdgeCases() {
        testModel.addAlbumToLibrary("19", "adele");
        boolean result = testModel.rateASong("tired", "adele", "jlkjlkj");
        Assert.assertEquals(false, result);
        boolean result2 = testModel.rateASong("tired", "adele", "99");
        Assert.assertEquals(false, result2);
        boolean result3 = testModel.rateASong("tired", "adele", "-1");
        Assert.assertEquals(false, result3);
        boolean result4 = testModel.rateASong("hghgh", "adele", "5");
        Assert.assertEquals(false, result4);
        boolean result5 = testModel.rateASong("tired", "pooprocket", "5");
        Assert.assertEquals(false, result5);
        boolean result6 = testModel.rateASong("tired", "adele", "3");
        Assert.assertEquals(true, result6);
    }

    @Test
    public void testGetSongsByArtistFromStore() {
        ArrayList<String> songList = testModel.getSongsByArtistFromStore("Adele");
        Assert.assertEquals(24, songList.size());
    }

    @Test
    public void testGetArtists() {
        testModel.addAlbumToLibrary("19", "adele");
        ArrayList<String> result = testModel.getArtists();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testAddAlbumToLibraryEdgeCases() {
        boolean result = testModel.addAlbumToLibrary("the album", "the artist");
        Assert.assertEquals(false, result);
        boolean result2 = testModel.addAlbumToLibrary("19", "adele");
        Assert.assertEquals(true, result2);
        boolean result3 = testModel.addAlbumToLibrary("19", "adele");
        Assert.assertEquals(true, result3);
    }

    @Test
    public void testGetAlbums() {
        testModel.addAlbumToLibrary("19", "adele");
        ArrayList<String> result = testModel.getAlbums();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testGetAlbumsByArtistFromStore() {
        ArrayList<String> albumList = testModel.getAlbumsByArtistFromStore("Adele");
        Assert.assertEquals(2, albumList.size());
    }
}
