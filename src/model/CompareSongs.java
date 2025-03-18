package src.model;

import src.store.Song;

import java.util.Comparator;

public class CompareSongs implements Comparator {
    @Override
    public int compare(Object song1, Object song2) {
        Song songa = (Song) song1;
        Song songb = (Song) song2;

        // making these reversed because we want the list reverse-sorted.
        if (songa.getPlays() < songb.getPlays()) {
            return 1; // song1 has less plays than song2
        }
        if (songa.getPlays() > songb.getPlays()) {
            return -1; // song1 has more plays than song2
        }
        return 0; // both songs have the same abount of plays
    }

}
