package src.store;

public class Song {
    private final String title;
    private final String artist;
    private final String album;
    private int rating;
    private boolean favorite;

    /*=============================================================================================
     *
     * Constructors
     *
     *===========================================================================================*/

    // Main Constructor
    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.rating = 0; // there is functionality so that if the rating is 0, it will say "no rating".
        this.favorite = false;
    }

    // Copy Constructor
    public Song(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.rating = song.getRating();
        this.album = song.getAlbum();
        this.favorite = song.getFavorite();
    }

    /*=============================================================================================
     *
     * Methods
     *
     *===========================================================================================*/

    /* Takes input integer between 1-5 */
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setFavorite() {this.favorite = true;}

    /*=============================================================================================
     *
     * Getters
     *
     *===========================================================================================*/

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
    public boolean getFavorite() {return this.favorite;}

    /*=============================================================================================
     *
     * Overrides
     *
     *===========================================================================================*/

    @Override
    // <Song Name> - <Artist> - <Album>
    public String toString() {
        return this.title + " - " + this.artist + " - " + this.album;
    }
}
