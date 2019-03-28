package nl.han.oose.service;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.playlists.Playlists;
import nl.han.oose.persistence.AccountDAO;
import nl.han.oose.persistence.PlaylistsDAO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.service.playlistService.PlaylistsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class PlaylistServiceTest {

    @Mock
    PlaylistsDAO playlistsDAO;

    @Mock
    AccountDAO accountDAO;

    @Mock
    TokenDAO tokenDAO;

    @InjectMocks
    PlaylistsServiceImpl sut;

    /**
     * Test for getAllPlaylists
     */
    @Test
    public void testThatPlaylistsAreReturnedGetAllPlaylists() throws TokenExpiredException {
        String token = "123-123-123";
        Account account = new Account("reno", "rovers");
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(accountDAO.getUsernameThroughToken(token)).thenReturn(account.getUser());
        Mockito.when(playlistsDAO.getAllPlaylists("reno")).thenReturn(new Playlists());

        Playlists returnedPlaylists = sut.getAllPlaylists(token);
        Assert.assertEquals(playlists.getClass(), returnedPlaylists.getClass());
    }

    @Test( expected = TokenExpiredException.class )
    public void testThatExceptionIsThrownGetAllPlaylists() throws TokenExpiredException {
        String token = "123-123-123";

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.getAllPlaylists(token);
    }

    /**
     * Tests for deletePlaylist
     */
    @Test
    public void testThatPlaylistsAreReturnedDeletePlaylist() throws TokenExpiredException {
        int playlistId = 1;
        String token = "123-123-123";
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(accountDAO.getUsernameThroughToken(token)).thenReturn("username");
        Mockito.when(playlistsDAO.getAllPlaylists("username")).thenReturn(new Playlists());
        Mockito.doNothing().when(playlistsDAO).deletePlaylist(playlistId);

        Playlists returnedPlaylists = sut.deletePlaylist(playlistId, token);
        Assert.assertEquals(playlists.getClass(), returnedPlaylists.getClass());
    }

    @Test( expected = TokenExpiredException.class )
    public void testThatExceptionIsThrownDeletePlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.deletePlaylist(playlistId, token);
    }

    /**
     * Tests for addPlaylist
     */
    @Test
    public void testThatPlaylistsAreReturnedAddPlaylist() throws TokenExpiredException {
        Playlist playlist = new Playlist();
        String token = "123-123-123";
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(accountDAO.getUsernameThroughToken(token)).thenReturn("username");
        Mockito.when(playlistsDAO.getAllPlaylists("username")).thenReturn(new Playlists());
        Mockito.doNothing().when(playlistsDAO).addPlaylist(playlist, "username");

        Playlists returnedPlaylists = sut.addPlaylist(playlist, token);
        Assert.assertEquals(playlists.getClass(), returnedPlaylists.getClass());
    }

    @Test( expected = TokenExpiredException.class )
    public void testThatExceptionIsThrownAddPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        Playlist playlist = new Playlist();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.addPlaylist(playlist, token);
    }

    /**
     * Test for editPlaylist
     */
    @Test
    public void testThatPlaylistsAreReturnedEditPlaylist() throws TokenExpiredException {
        Playlist playlist = new Playlist();
        String token = "123-123-123";
        int playlistId = 1;
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(false);
        Mockito.when(accountDAO.getUsernameThroughToken(token)).thenReturn("username");
        Mockito.when(playlistsDAO.getAllPlaylists("username")).thenReturn(new Playlists());
        Mockito.doNothing().when(playlistsDAO).editPlaylist(playlist, playlistId);

        Playlists returnedPlaylists = sut.editPlaylist(playlist, playlistId, token);
        Assert.assertEquals(playlists.getClass(), returnedPlaylists.getClass());
    }

    @Test( expected = TokenExpiredException.class )
    public void testThatExceptionIsThrownEditPlaylist() throws TokenExpiredException {
        String token = "123-123-123";
        int playlistId = 1;
        Playlist playlist = new Playlist();

        Mockito.when(tokenDAO.checkIfTokenIsValid(token)).thenReturn(true);
        sut.editPlaylist(playlist, playlistId, token);
    }
}
