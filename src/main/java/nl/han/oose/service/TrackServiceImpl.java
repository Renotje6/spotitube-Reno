package nl.han.oose.service;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Tracks;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TrackServiceImpl implements TrackService {

    @Inject
    TokenDAO tokenDAO;

    @Inject
    TrackDAO trackDAO;

    @Override
    public Tracks getAllTrackInPlaylist(int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            return trackDAO.getTracksInPlaylist(playlistId);
        } else {
            throw new TokenExpiredException("Token expired");
        }
    }
}
