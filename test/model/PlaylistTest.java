package test.model;
import org.junit.Assert;
import src.model.Playlist;
import src.store.Album;
import src.store.MusicStore;
import org.junit.Test;
import src.store.Song;
import java.util.ArrayList;

public class PlaylistTest {
    private Playlist playlist = new Playlist("Squib Gams");

    @Test
    public void testAdd() {
        playlist.addSong(new Song("My Mathematical Romance","Shaun McGonagal","Life"));
        Assert.assertEquals("Life", playlist.getSongs().getFirst().getAlbum());
    }

    @Test
    public void testRemove() {
        Song song = new Song("My Mathematical Romance","Shaun McGonagal","Life");
        playlist.addSong(song);
        playlist.removeSong(song);
        Assert.assertTrue(playlist.getSongs().isEmpty());
    }

    @Test
    public void testToString() {
        Song song = new Song("My Mathematical Romance","Shaun McGonagal","Life");
        playlist.addSong(song);
        Assert.assertEquals("Playlist: Squib Gams\nMy Mathematical Romance - Shaun McGonagal\n", playlist.toString());
    }
}