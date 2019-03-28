package nl.han.oose.entity.tracks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrackTest {

    Track track;

    @Before
    public void init(){
        track = new Track();
    }

    @Test
    public void testThatIdHasBeenChanged(){
        track.setId(5);

        Assert.assertEquals(5, track.getId());
    }

    @Test
    public void testThatPerformerHasBeenChanged(){
        track.setPerformer("Some performer");

        Assert.assertEquals("Some performer", track.getPerformer());
    }

    @Test
    public void testThatTitleHasBeenChanged(){
        track.setTitle("Title");

        Assert.assertEquals("Title", track.getTitle());
    }

    @Test
    public void testThatURLHasBeenChanged(){
        track.setUrl("Some URL");

        Assert.assertEquals("Some URL", track.getUrl());
    }

    @Test
    public void testThatDurationHasBeenChanged(){
        track.setDuration(10);

        Assert.assertEquals(10, track.getDuration());
    }

    @Test
    public void testThatOfflineAvailableHasBeenChanged(){
        track.setOfflineAvailable(true);

        Assert.assertTrue(track.isOfflineAvailable());
    }

    @Test
    public void testThatPlayCountHasBeenChanged(){
        track.setPlaycount(10);

        Assert.assertEquals(10, track.getPlaycount());
    }
}
