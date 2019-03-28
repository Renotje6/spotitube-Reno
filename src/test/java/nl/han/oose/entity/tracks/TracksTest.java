package nl.han.oose.entity.tracks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TracksTest {
    Tracks tracks;

    @Before
    public void init(){
        tracks = new Tracks();
    }

    @Test
    public void testThatConstructorWorks(){
        List<Track> trackList = new ArrayList<>();
        trackList.add(new Track());
        Tracks tracks = new Tracks(trackList);

        Assert.assertEquals(trackList.get(0), tracks.getTracks().get(0));
    }

    @Test
    public void testThatTracksHasBeenChanged(){
        List<Track> trackList = new ArrayList<>();
        trackList.add(new Track());
        tracks.setTracks(trackList);

        Assert.assertEquals(trackList.get(0), tracks.getTracks().get(0));
    }
}
