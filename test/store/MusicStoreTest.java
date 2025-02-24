package test.store;

import org.junit.Assert;
import src.store.MusicStore;
import org.junit.Test;
import src.store.Song;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class MusicStoreTest {
    MusicStore myMusicStore = new MusicStore();

    @Test
    public void testGetSongByTitle() {
        // testing with the song Clocks by Coldplay:
        Song currSong = myMusicStore.getSongByTitle("Clocks");
        Assert.assertTrue(currSong != null);
        Assert.assertTrue(currSong.getTitle().equals("clocks"));
        Assert.assertTrue(currSong.getArtist().equals("coldplay"));
        Assert.assertTrue(currSong.getAlbum().equals("a rush of blood to the head"));
        Assert.assertTrue(currSong.getRating() == 0);
    }

    @Test
    public void testGetSongsByArtist() {
        // testing with all current songs by Adele (there are two Adele albums in the albums directory)
        ArrayList<Song> currSongList = myMusicStore.getSongsByArtist("Adele");
        Assert.assertTrue(currSongList.size() == 24);
        Assert.assertEquals("daydreamer", currSongList.get(0).getTitle());
    }
}
