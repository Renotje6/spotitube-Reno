package nl.han.oose.entity.tracks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SongTest {
    Song song;

    @Before
    public void init(){
        song = new Song();
    }

    @Test
    public void testThatConstructorWorks(){
        Song song = new Song(1, "performer", "title", "Some url", 30, 1, false, "new album");

        Assert.assertEquals(1, song.getId());
        Assert.assertEquals("performer", song.getPerformer());
        Assert.assertEquals("title", song.getTitle());
        Assert.assertEquals("Some url", song.getUrl());
        Assert.assertEquals(30, song.getDuration());
        Assert.assertEquals(1, song.getPlaycount());
        Assert.assertFalse(song.isOfflineAvailable());
        Assert.assertEquals("new album", song.getAlbum());
    }

    @Test
    public void testThatAlbumHasBeenChanged(){
        song.setAlbum("Newest album");
        Assert.assertEquals("Newest album", song.getAlbum());
    }
}
