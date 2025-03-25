package test.model;

import org.junit.Assert;
import src.model.LibraryModel;
import org.junit.Test;
import src.store.MusicStore;
import src.store.Song;
import src.model.CompareSongs;

import java.util.Comparator;

public class CompareSongsTest {

    private Song song1 = new Song("", "", "");
    private Song song2 = new Song("", "", "");
    private Comparator songc = new CompareSongs();

    @Test
    public void test1() {
        song1.play();
        song2.play();
        song2.play();
        Assert.assertFalse(songc.compare((Object)song1, (Object)song2) < 0);
    }

    @Test
    public void test2() {
        song1.play();
        song1.play();
        song2.play();
        Assert.assertTrue(songc.compare((Object)song1, (Object)song2) < 0);
    }

    @Test
    public void test3() {
        song1.play();
        song2.play();
        Assert.assertEquals(0, songc.compare((Object)song1, (Object)song2));
    }
}