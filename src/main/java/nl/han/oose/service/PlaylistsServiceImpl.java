package nl.han.oose.service;

import nl.han.oose.entity.playlists.Playlists;
import nl.han.oose.persistence.PlaylistsDAO;
import nl.han.oose.persistence.TokenDAO;

import javax.inject.Inject;

public class PlaylistsServiceImpl implements PlaylistsService {

    @Inject
    PlaylistsDAO playlistsDAO;

    @Inject
    TokenDAO tokenDAO;

    @Override
    public Playlists getAllPlaylists(String token) {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {

        } else {
            //TODO: throw tokenExpiredException
        }
    }
}
