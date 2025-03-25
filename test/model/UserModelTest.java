package test.model;

import org.junit.Test;
import org.junit.Assert;
import src.model.UserModel;
import src.store.MusicStore;

import static org.junit.Assert.assertTrue;

public class UserModelTest {
    MusicStore myStore = new MusicStore();
    UserModel myUserModel = new UserModel(myStore);

    /*@Test
    // NOT A REAL TEST CASE, I AM JUST TESTING RANDOM THINGS HERE WITH PRINTING TO CONSOLE
    public void placeholderTest() {
        System.out.println(myUserModel.getNumUsers());
        myUserModel.addUser("chris", "mynameischris");
        System.out.println(myUserModel.getNumUsers());
        myUserModel.addUser("jonathan", "passwd");
        System.out.println(myUserModel.getNumUsers());

        System.out.println(myUserModel.getLoggedIn());
        System.out.println(myUserModel.login("chri", "mynameischris"));
        System.out.println(myUserModel.getLoggedIn());
        System.out.println(myUserModel.login("chris", "mynameischris"));
        System.out.println(myUserModel.getLoggedIn());
    }*/

    @Test
    public void testGetNumUsers() {
        myUserModel.addUser("chris", "mynameischris");
        Assert.assertTrue(10 <= myUserModel.getNumUsers());
    }

    @Test
    public void testLoginUserExists() {
        myUserModel.login("chris", "mynameischris");
        Assert.assertTrue(myUserModel.getLoggedIn());
    }

    @Test
    public void testLoginNoUserExists() {
        myUserModel.login("chri", "mynameischris");
        Assert.assertFalse(myUserModel.getLoggedIn());
    }

    @Test
    public void testLogout() {
        myUserModel.login("chris", "mynameischris");
        myUserModel.logout();
        Assert.assertFalse(myUserModel.getLoggedIn());
    }

    @Test
    public void testWrongPassword() {
        myUserModel.login("chris", "thisisjustsomerandomstringthatisntchrisspassword");
        Assert.assertFalse(myUserModel.getLoggedIn());
    }

    @Test
    public void testAddSong() {
        myUserModel.login("chris", "mynameischris");
        assertTrue(myUserModel.addSongToLibrary("first love", "adele"));
    }

    @Test
    public void testPlayASong() {
        myUserModel.login("chris", "mynameischris");
        myUserModel.addAlbumToLibrary("19", "adele");
        assertTrue(myUserModel.playASong("first love", "adele"));
    }

    @Test
    public void testRecentlyPlayedNone() {
        myUserModel.login("chris", "mynameischris");
        Assert.assertEquals("No songs played yet", myUserModel.getRecentlyPlayed());
    }

    @Test
    public void testRecentlyPlayedSome() {
        myUserModel.login("chris", "mynameischris");
        myUserModel.addAlbumToLibrary("19", "adele");
        myUserModel.playASong("first love", "adele");
        Assert.assertNotEquals("No songs played yet", myUserModel.getRecentlyPlayed());
    }

    @Test
    public void testFrequentlyPlayedNone() {
        myUserModel.login("chris", "mynameischris");
        Assert.assertEquals("No songs played yet", myUserModel.getFrequentlyPlayed());
    }

    @Test
    public void testFrequentlyPlayedSome() {
        myUserModel.login("chris", "mynameischris");
        myUserModel.addAlbumToLibrary("19", "adele");
        myUserModel.playASong("first love", "adele");
        Assert.assertNotEquals("No songs played yet", myUserModel.getFrequentlyPlayed());
    }

    @Test
    public void testPlayASongDNE() {
        myUserModel.login("chris", "mynameischris");
        myUserModel.addAlbumToLibrary("19", "adele");
        Assert.assertFalse(myUserModel.playASong("last love", "adele"));

    }
}
