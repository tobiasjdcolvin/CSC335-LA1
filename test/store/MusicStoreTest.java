package test.store;

import org.junit.Assert;
import src.store.Album;
import src.store.MusicStore;
import org.junit.Test;
import src.store.Song;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class MusicStoreTest {
    MusicStore myMusicStore = new MusicStore();

    @Test
    public void testGetSongsByTitle() {
        // testing with the song Clocks by Coldplay:
        Song currSong = myMusicStore.getSongsByTitle("Clocks").get(0);
        Assert.assertTrue(currSong != null);
        Assert.assertTrue(currSong.getTitle().equals("clocks"));
        Assert.assertTrue(currSong.getArtist().equals("coldplay"));
        Assert.assertTrue(currSong.getAlbum().equals("a rush of blood to the head"));
        Assert.assertTrue(currSong.getRating() == 0);
    }

    @Test
    public void testGetSongsByTitleWhenNotExist() {
        // testing when the song does not exist
        ArrayList<Song> currSongList = myMusicStore.getSongsByTitle("NotASong");
        Assert.assertTrue(currSongList.size() == 0);
    }

    @Test
    public void testGetSongsByArtist() {
        // testing with all current songs by Adele (there are two Adele albums in the albums directory)
        ArrayList<Song> currSongList = myMusicStore.getSongsByArtist("Adele");
        Assert.assertTrue(currSongList.size() == 24);
        Assert.assertEquals("daydreamer", currSongList.get(0).getTitle());
    }

    @Test
    public void testGetSongsByArtistWhenNotExist() {
        // testing when the artist does not exist
        ArrayList<Song> currSongList = myMusicStore.getSongsByArtist("NotAnArtist");
        Assert.assertTrue(currSongList.size() == 0);
    }

    @Test
    public void testGetAlbumsByTitle() {
        // testing with the album Begin Again by Norah Jones:
        Album currAlbum = myMusicStore.getAlbumsByTitle("Begin Again").get(0);
        Assert.assertTrue(currAlbum.getArtist().equals("norah jones"));
    }

    @Test
    public void testGetAlbumsByTitleWhenNotExist() {
        // testing when the album does not exist
        ArrayList<Album> currAlbumList = myMusicStore.getAlbumsByTitle("NotAnAlbum");
        Assert.assertTrue(currAlbumList.size() == 0);
    }

    @Test
    public void testGetAlbumsByArtist() {
        // testing with all albums by Adele (there are two Adele albums in the albums directory)
        ArrayList<Album> currAlbumList = myMusicStore.getAlbumsByArtist("Adele");
        String checkFor = "21";
        boolean found = false;
        for (Album a : currAlbumList) {
            if (a.getTitle().equals(checkFor)) {
                found = true;
            }
        }
        Assert.assertEquals(2, currAlbumList.size());
        Assert.assertEquals(true, found);
    }

    @Test
    public void testGetAlbumsByArtistWhenNotExist() {
        // testing when the artist does not exist
        ArrayList<Album> currAlbumList = myMusicStore.getAlbumsByArtist("NotAnArtist");
        Assert.assertTrue(currAlbumList.size() == 0);
    }

    @Test
    public void testSongWithMultipleArtists(){
        // Tests that things work when more than one artist has a song with the same name.
        // In this case, "Lullaby" is a son by two artists: OneRepublic and Leonard Cohen.
        ArrayList<Song> currSongList = myMusicStore.getSongsByTitle("Lullaby");
        Assert.assertTrue(currSongList.size() == 2);
    }
}
