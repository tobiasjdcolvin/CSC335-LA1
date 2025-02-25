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

    @Test
    public void testGetAlbumByTite() {
        // testing with the album Begin Again by Norah Jones:
        Album currAlbum = myMusicStore.getAlbumByTitle("Begin Again");
        // TODO: actually test stuff instead of printing string
        System.out.println(currAlbum.toString());
    }

    @Test
    public void testGetAlbumsByTitle() {
        // testing with all albums by Adele (there are two Adele albums in the albums directory)
        ArrayList<Album> currAlbumList = myMusicStore.getAlbumsByArtist("Adele");
        // TODO: actually test stuff instead of printing string
        for (Album a : currAlbumList) {
            System.out.println(a.toString());
        }
        Assert.assertEquals(2, currAlbumList.size());
    }
}
