package pl.sda.intermediate.playlista;

import org.junit.Test;

public class PlayListTest {

    @Test
    public void sequenceAndRandom() {

        // Given
        Playlist playlist = new Playlist();
        Playlist playListInside = new Playlist();

        Music song = new Music("In the end", "Numb");
        Movie movie = new Movie("Pulp fiction");
        Music loveSong = new Music("Love song", "Ariana");
        Movie actionMOvie = new Movie("Titanic");

        playlist.AddToPlaylist(song);
        playlist.AddToPlaylist(movie);
        playlist.AddToPlaylist(loveSong);
        playlist.AddToPlaylist(actionMOvie);

        playListInside.AddToPlaylist(loveSong);
        playListInside.AddToPlaylist(song);
        playListInside.choosePlayType(PlayType.RANDOM);

        playlist.AddToPlaylist(playListInside);

        // When
        System.out.println(playlist.play());

    }
}
