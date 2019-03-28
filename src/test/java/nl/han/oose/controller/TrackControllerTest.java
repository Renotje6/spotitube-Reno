package nl.han.oose.controller;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Tracks;
import nl.han.oose.service.trackService.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith( MockitoJUnitRunner.class )
public class TrackControllerTest {

    @Mock
    TrackServiceImpl trackService;

    @InjectMocks
    TrackController sut;

    /**
     * Tests for getAllTracksNotInPlaylist
     */
    @Test
    public void testThatOKStatusCodeAndAllTracksNotInPlaylistAreReturned() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(trackService.getAllTracksNotInPlaylist(playlistId, token)).thenReturn(tracks);
        Response response = sut.getAllTracksNotInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(tracks.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturned() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(trackService.getAllTracksNotInPlaylist(playlistId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.getAllTracksNotInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturned(){
        String token = "";
        int playlistId = 0;

        Response response = sut.getAllTracksNotInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
