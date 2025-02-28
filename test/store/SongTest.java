package test.store;

import org.junit.Assert;
import org.junit.Test;
import src.store.Song;

public class SongTest {
    private Song song = new Song("Amazon","Bernarldo","Christmas");

    @Test
    public void testCopy() {
        Song song2 = new Song(song);
        Assert.assertEquals(song.getArtist()+song.getTitle()+song.getAlbum(),
                song2.getArtist()+song2.getTitle()+song2.getAlbum());
    }

    @Test
    public void testRating() {
        song.setRating(5);
        Assert.assertEquals(5, song.getRating());
    }

    @Test
    public void testSetRatingEdgeCases() {
        song.setRating(1);
        Assert.assertEquals(1, song.getRating());
        song.setRating(2);
        Assert.assertEquals(2, song.getRating());
        song.setRating(3);
        Assert.assertEquals(3, song.getRating());
        song.setRating(4);
        Assert.assertEquals(4, song.getRating());
    }

    @Test
    public void testString() {
        Assert.assertEquals("Amazon - Bernarldo - Christmas", song.toString());
    }

    @Test
    public void testFavorite() {
        song.setFavorite();
        Assert.assertTrue(song.getFavorite());
    }

    @Test
    public void testNotFavorite() {
        Assert.assertFalse(song.getFavorite());
    }


}