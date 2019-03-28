package nl.han.oose.entity.playlists;

import nl.han.oose.entity.tracks.Track;
import nl.han.oose.entity.tracks.Tracks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaylistTest {
    Playlist playlist;
    Tracks tracks;

    @Before
    public void init(){
        tracks = new Tracks(new ArrayList<Track>());
        playlist = new Playlist(1, "Rock", true, tracks.getTracks());
    }

    @Test
    public void testThatPlaylistConstructorWorks(){
        Assert.assertEquals(1, playlist.getId());
        Assert.assertEquals("Rock", playlist.getName());
        Assert.assertTrue(playlist.isOwner());
        Assert.assertEquals(tracks.getTracks(), playlist.getTracks());
    }

    @Test
    public void testThatIdHasBeenChanged(){
        playlist.setId(5);

        Assert.assertEquals(5, playlist.getId());
    }

    @Test
    public void testThatNameHasBeenChanged(){
        String newName = "Metal";
        playlist.setName(newName);

        Assert.assertEquals(newName, playlist.getName());
    }

    @Test
    public void testThatOwnerHasBeenChanged(){
        playlist.setOwner(false);

        Assert.assertFalse(playlist.isOwner());
    }

    @Test
    public void testThatTracksHasBeenChanged(){
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track(1, "Performer", "Title", "SOME URL", 23, 1, true));
        playlist.setTracks(tracks);

        Assert.assertEquals(tracks.get(0), playlist.getTracks().get(0));
    }
}
