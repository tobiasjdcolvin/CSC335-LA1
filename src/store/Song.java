package src.store;

public class Song {
    private final String title;
    private final String artist;
    private final String album;
    private int rating;

    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.rating = 0;
    }

    /*
        Takes input integer between 1-5
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /* Getters */
    public String getTitle() {
        return this.title;
    }
    public String getArtist() {
        return this.artist;
    }
    public String getAlbum() {return this.album;}
    public int getRating() {
        return this.rating;
    }
}
