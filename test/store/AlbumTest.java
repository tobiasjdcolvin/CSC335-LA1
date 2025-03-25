package test.store;

import org.junit.Assert;
import src.store.Album;
import src.store.MusicStore;
import org.junit.Test;
import src.store.Song;
import java.util.ArrayList;


public class AlbumTest {
    private final Album album = new Album("Bum Comma Al", "Bum Al", "Albius bumbius");

    @Test
    public void testStrungOut() {
        Assert.assertEquals("Bum Comma Al: Bum Al", album.toString());
    }

    @Test
    public void testAddSong() {
        album.addSong(new Song("Tweenty Five Point Five Five Five Nine Help Me", "Bum Al", "Bum Comma Al"));
        Assert.assertEquals("Bum Comma Al: Bum Al\nTweenty Five Point Five Five Five Nine Help Me", album.toString());
    }

    @Test
    public void testClone() {
        album.addSong(new Song("Tweenty Five Point Five Five Five Nine Help Me", "Bum Al", "Bum Comma Al"));
        Album album2 = new Album(album);
        Assert.assertEquals("Bum Comma Al: Bum Al\nTweenty Five Point Five Five Five Nine Help Me", album2.toString());
    }

    @Test
    public void testGetSongs() {
        Song song = new Song("Tweenty Five Point Five Five Five Nine Help Me", "Bum Al", "Bum Comma Al");
        album.addSong(song);
        Assert.assertEquals(song.getTitle(), album.getSongs().getFirst().getTitle());
    }

    @Test
    public void testRemoveSongNone() {
        album.removeSong("Never gonna give you up", "Rick Astley");
        Assert.assertTrue(album.getSongs().isEmpty());
    }

    @Test
    public void testRemoveSongSome() {
        album.addSong(new Song("Never gonna give you up", "Rick Astley", "Fortnite Battle Pass"));
        album.removeSong("Never gonna give you up", "Rick Astley");
        Assert.assertTrue(album.getSongs().isEmpty());
    }

    @Test
    public void testRemoveSongSomeNone() {
        album.addSong(new Song("Never gonna give you up", "Rick Astley", "Fortnite Battle Pass"));
        album.addSong(new Song("Never gonna give you up2", "Rick Astley2", "Fortnite Battle Pass2"));
        album.removeSong("Never gonna give you up", "Rick Astley");
        Assert.assertFalse(album.getSongs().isEmpty());
    }
}