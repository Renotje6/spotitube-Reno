package nl.han.oose.service;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Track;
import nl.han.oose.entity.tracks.Tracks;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TrackDAO;
import nl.han.oose.service.trackService.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class TrackServiceTest {

    @Mock
    TrackDAO trackDAO;

    @Mock
    TokenDAO tokenDAO;

    @InjectMocks
    TrackServiceImpl sut;

    /**
     * Tests for getAllTracksInPlaylist
     */
    @Test
    public void testThatTracksAreReturned() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(trackDAO.getTracksInPlaylist(playlistId, true)).thenReturn(tracks);

        Tracks returnedTracks = sut.getAllTracksInPlaylist(playlistId, token);
        Assert.assertEquals(tracks.getClass(), returnedTracks.getClass());
    }

    @Test(expected = TokenExpiredException.class)
    public void testThatExceptionisThrown() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.getAllTracksInPlaylist(playlistId, token);
    }

    /**
     * Tests for getAllTracksNotInPlaylist
     */
    @Test
    public void testThatTracksAreReturnedNotInPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(trackDAO.getTracksInPlaylist(playlistId, false)).thenReturn(tracks);

        Tracks returnedTracks = sut.getAllTracksNotInPlaylist(playlistId, token);
        Assert.assertEquals(tracks.getClass(), returnedTracks.getClass());
    }

    @Test(expected = TokenExpiredException.class)
    public void testThatExceptionisThrownNotInPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.getAllTracksNotInPlaylist(playlistId, token);
    }

    /**
     * Test for deleteTrackFromPlaylist
     */
    @Test
    public void testThatTracksAreReturnedWhenDeleteTrack() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        int trackId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.doNothing().when(trackDAO).deleteTrackFromPlaylist(playlistId, trackId);
        Mockito.when(trackDAO.getTracksInPlaylist(playlistId, true)).thenReturn(new Tracks());

        Tracks returnedTracks = sut.deleteTrackFromPlaylist(playlistId, trackId, token);
        Assert.assertEquals(tracks.getClass(), returnedTracks.getClass());
    }

    @Test(expected = TokenExpiredException.class)
    public void testThatExceptionisThrownDeleteTrack() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        int trackId = 1;

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.deleteTrackFromPlaylist(playlistId, trackId, token);
    }

    /**
     * Tests for addTrackToPlaylist
     */
    @Test
    public void testThatTracksAreReturnedWhenAddTrack() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Track track = new Track();
        Tracks tracks = new Tracks();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.doNothing().when(trackDAO).addTrackToPlaylist(track, playlistId);
        Mockito.when(trackDAO.getTracksInPlaylist(playlistId, true)).thenReturn(new Tracks());

        Tracks returnedTracks = sut.addTrackToPlaylist(track, playlistId, token);
        Assert.assertEquals(tracks.getClass(), returnedTracks.getClass());
    }

    @Test(expected = TokenExpiredException.class)
    public void testThatExceptionisThrownAddTrack() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Track track = new Track();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.addTrackToPlaylist(track, playlistId, token);
    }
}
