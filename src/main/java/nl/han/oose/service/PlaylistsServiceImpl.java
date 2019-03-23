package nl.han.oose.service;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.playlists.Playlists;
import nl.han.oose.persistence.AccountDAO;
import nl.han.oose.persistence.PlaylistsDAO;
import nl.han.oose.persistence.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistsServiceImpl implements PlaylistsService {

    @Inject
    PlaylistsDAO playlistsDAO;

    @Inject
    TokenDAO tokenDAO;

    @Inject
    AccountDAO accountDAO;

    @Override
    public Playlists getAllPlaylists(String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            String username = accountDAO.getUsernameThroughToken(token);
            return playlistsDAO.getAllPlaylists(username);
        } else {
            tokenDAO.deleteToken(token);
            throw new TokenExpiredException("Token expired!");
        }
    }

    @Override
    public Playlists deletePlaylist(int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            String username = accountDAO.getUsernameThroughToken(token);
            playlistsDAO.deletePlaylist(playlistId);
            return playlistsDAO.getAllPlaylists(username);
        } else {
            tokenDAO.deleteToken(token);
            throw new TokenExpiredException("Token expired!");
        }
    }

    @Override
    public Playlists addPlaylist(Playlist playlistToAdd, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            String username = accountDAO.getUsernameThroughToken(token);
            playlistsDAO.addPlaylist(playlistToAdd, username);
            return playlistsDAO.getAllPlaylists(username);
        } else {
            tokenDAO.deleteToken(token);
            throw new TokenExpiredException("Token expired!");
        }
    }

    @Override
    public Playlists editPlaylist(Playlist playlist, int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            String username = accountDAO.getUsernameThroughToken(token);
            playlistsDAO.editPlaylist(playlist, playlistId);
            return playlistsDAO.getAllPlaylists(username);
        } else {
            tokenDAO.deleteToken(token);
            throw new TokenExpiredException("Token expired!");
        }
    }
}
