package src.store;

public class Song {
    private final String title;
    private final String artist;
    private final String album;
    private Rating rating;
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
        this.rating = Rating.NONE; // there is functionality so that if the rating is 0, it will say "no rating".
        this.favorite = false;
    }

    // Copy Constructor
    public Song(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.setRating(song.getRating());
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
        if (rating == 1) {
            this.rating = Rating.ONE;
        } else if (rating == 2) {
            this.rating = Rating.TWO;
        } else if (rating == 3) {
            this.rating = Rating.THREE;
        } else if (rating == 4) {
            this.rating = Rating.FOUR;
        } else if (rating == 5) {
            this.rating = Rating.FIVE;
        } else {
            this.rating = Rating.NONE;
        }
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
        if (this.rating == Rating.ONE) {
            return 1;
        } else if (this.rating == Rating.TWO) {
            return 2;
        } else if (this.rating == Rating.THREE) {
            return 3;
        } else if (this.rating == Rating.FOUR) {
            return 4;
        } else if (this.rating == Rating.FIVE) {
            return 5;
        } else {
            return 0;
        }
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
