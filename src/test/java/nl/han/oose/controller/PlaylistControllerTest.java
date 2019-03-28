package nl.han.oose.controller;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.playlists.Playlists;
import nl.han.oose.entity.tracks.Track;
import nl.han.oose.entity.tracks.Tracks;
import nl.han.oose.service.playlistService.PlaylistsServiceImpl;
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
public class PlaylistControllerTest {

    @Mock
    PlaylistsServiceImpl playlistsService;

    @Mock
    TrackServiceImpl trackService;

    @InjectMocks
    PlaylistsController sut;

    /**
     * Tests for getAllPlaylists
     */
    @Test
    public void testThatAllPlaylistsAreReturned() throws TokenExpiredException {
        String token = "123-123-123";
        Playlists playlists = new Playlists();

        Mockito.when(playlistsService.getAllPlaylists(token)).thenReturn(playlists);
        Response response = sut.getPlaylists(token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(playlists.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatBadRequestWhenEmptyTokenGetAllPlaylists() {
        String token = "";

        Response response = sut.getPlaylists(token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedWhenTokenIsExpiredGetAllPlaylists() throws TokenExpiredException {
        String token = "123-123-123";

        Mockito.when(playlistsService.getAllPlaylists(token)).thenThrow(new TokenExpiredException());
        Response response = sut.getPlaylists(token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for deletePlaylist
     */
    @Test
    public void testThatDeletePlaylistReturnsOKStatus() throws TokenExpiredException {
        Playlists playlists = new Playlists();
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(playlistsService.deletePlaylist(playlistId, token)).thenReturn(playlists);
        Response response = sut.deletePlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(playlists.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedWhenTokenIsExpiredDeletePlaylist() throws TokenExpiredException {
        int playlistId = 1;
        String token = "123-123-123";

        Mockito.when(playlistsService.deletePlaylist(playlistId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.deletePlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturnedWhenPlaylistIdIsZero() {
        int playlistId = 0;
        String token = "123-123-123";

        Response response = sut.deletePlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for addPlaylists
     */
    @Test
    public void testThatOKStatusCodeAndPlaylistsAreReturnedAddPlaylist() throws TokenExpiredException {
        Playlist playlist = new Playlist();
        Playlists playlists = new Playlists();
        String token = "123-123-123";

        Mockito.when(playlistsService.addPlaylist(playlist, token)).thenReturn(playlists);
        Response response = sut.addPlaylist(playlist, token);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Assert.assertEquals(playlists.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatFORBIDDENStatusCodeIsReturnedAddPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        Playlist playlist = new Playlist();

        Mockito.when(playlistsService.addPlaylist(playlist, token)).thenThrow(new TokenExpiredException());
        Response response = sut.addPlaylist(playlist, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestWhenEmptyTokenAddPlaylist() {
        String token = "";
        Playlist playlist = new Playlist();

        Response response = sut.addPlaylist(playlist, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for editPlaylist
     */
    @Test
    public void testThatOKStatusAndPlaylistsAreReturnedEditPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        Playlist playlist = new Playlist();
        int playlistId = 1;
        Playlists playlists = new Playlists();

        Mockito.when(playlistsService.editPlaylist(playlist, playlistId, token)).thenReturn(playlists);
        Response response = sut.editPlaylist(playlist, playlistId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(playlists.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedEditPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        Playlist playlist = new Playlist();
        int playlistId = 1;

        Mockito.when(playlistsService.editPlaylist(playlist, playlistId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.editPlaylist(playlist, playlistId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturnedEditPlaylist() {
        String token = "";
        Playlist playlist = new Playlist();
        int playlistId = 0;

        Response response = sut.editPlaylist(playlist, playlistId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for getAllTracksInPlaylist
     */
    @Test
    public void testThatOKStatusCodeAndAllTracksInPlaylistIsReturnedGetAllTracksInPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(trackService.getAllTracksInPlaylist(playlistId, token)).thenReturn(tracks);
        Response response = sut.getAllTracksInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(tracks.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedGetAllTracksInPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(trackService.getAllTracksInPlaylist(playlistId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.getAllTracksInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturnedGetAllTracksInPlaylist() {
        String token = "";
        int playlistId = 0;

        Response response = sut.getAllTracksInPlaylist(playlistId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for deleteTrackFromPlaylist
     */
    @Test
    public void testThatOKStatusCodeAndAllTracksInPlaylistAreReturnedDeleteTrackFromPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        int trackId = 1;
        Tracks tracks = new Tracks();

        Mockito.when(trackService.deleteTrackFromPlaylist(playlistId, trackId, token)).thenReturn(tracks);
        Response response = sut.deleteTrackFromPlaylist(playlistId, trackId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(tracks.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedDeleteTrackFromPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        int trackId = 1;

        Mockito.when(trackService.deleteTrackFromPlaylist(playlistId, trackId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.deleteTrackFromPlaylist(playlistId, trackId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturnedDeleteTrackFromPlaylist(){
        String token = "";
        int playlistId = 0;
        int trackId = 0;

        Response response = sut.deleteTrackFromPlaylist(playlistId, trackId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Tests for addTrackToPlaylist
     */
    @Test
    public void testThatOKStatusCodeAndTracksInPlaylistAreReturnedAddTrackToPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Track track = new Track();
        Tracks tracks = new Tracks();

        Mockito.when(trackService.addTrackToPlaylist(track, playlistId, token)).thenReturn(tracks);
        Response response = sut.addTrackToPlaylist(track, playlistId, token);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(tracks.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatForbiddenStatusCodeIsReturnedAddTrackToPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Track track = new Track();

        Mockito.when(trackService.addTrackToPlaylist(track, playlistId, token)).thenThrow(new TokenExpiredException());
        Response response = sut.addTrackToPlaylist(track, playlistId, token);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatBadRequestStatusCodeIsReturnedAddTrackToPlaylist(){
        String token = "";
        int playlistId = 0;
        Track track = new Track();

        Response response = sut.addTrackToPlaylist(track, playlistId, token);
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}