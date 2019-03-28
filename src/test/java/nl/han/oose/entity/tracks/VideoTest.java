package nl.han.oose.entity.tracks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VideoTest {
    Video video;

    @Before
    public void init(){
        video = new Video();
    }


    @Test
    public void testThatConstructorWorks(){
        Video video = new Video(1, "performer", "title", "Some url", 30, 1, false, "12-01-2012", "Some video");

        Assert.assertEquals(1, video.getId());
        Assert.assertEquals("performer", video.getPerformer());
        Assert.assertEquals("title", video.getTitle());
        Assert.assertEquals("Some url", video.getUrl());
        Assert.assertEquals(30, video.getDuration());
        Assert.assertEquals(1, video.getPlaycount());
        Assert.assertFalse(video.isOfflineAvailable());
        Assert.assertEquals("12-01-2012", video.getPublicationDate());
        Assert.assertEquals("Some video", video.getDescription());
    }

    @Test
    public void testThatPublicationDateHasBeenChanged(){
        video.setPublicationDate("12-12-2011");

        Assert.assertEquals("12-12-2011", video.getPublicationDate());
    }

    @Test
    public void testThatDescriptionHasBeenChanged(){
        video.setDescription("some video");

        Assert.assertEquals("some video", video.getDescription());
    }
}
