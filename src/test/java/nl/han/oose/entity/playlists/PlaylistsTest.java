package nl.han.oose.entity.playlists;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsTest {
    Playlists playlists;

    @Before
    public void init() {
        playlists = new Playlists();
    }

    @Test
    public void testThatConstructorWorks() {
        List<Playlist> playlistsList = new ArrayList<>();
        playlistsList.add(new Playlist());
        Playlists playlists = new Playlists(playlistsList, 10);

        Assert.assertEquals(playlistsList, playlists.getPlaylists());
        Assert.assertEquals(10, playlists.getLength());
    }

    @Test
    public void testThatPlaylistsHasBeenChanged() {
        List<Playlist> playlistsList = new ArrayList<>();
        playlistsList.add(new Playlist());

        playlists.setPlaylists(playlistsList);
        Assert.assertEquals(playlistsList, playlists.getPlaylists());
    }

    @Test
    public void testThatLengthHasBeenChanged() {
        playlists.setLength(30);
        Assert.assertEquals(30, playlists.getLength());
    }
}
