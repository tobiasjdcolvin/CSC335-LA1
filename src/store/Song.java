package src.store;

public class Song {
    private final String title;
    private final String artist;
    private int rating;

    /* Constructors */
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.rating = 0;
    }
    public Song(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.rating = song.getRating();
    }

    /* Takes input integer between 1-5 */
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
    public int getRating() {
        return this.rating;
    }

    /* Overrides */
    @Override
    public String toString() {
        return this.title + " - " + this.artist;
    }
}
