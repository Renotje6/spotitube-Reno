package nl.han.oose.service.trackService;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Track;
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
    public Tracks getAllTracksInPlaylist(int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            return trackDAO.getTracksInPlaylist(playlistId, true);
        } else {
            throw new TokenExpiredException("Token expired");
        }
    }

    @Override
    public Tracks getAllTracksNotInPlaylist(int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            return trackDAO.getTracksInPlaylist(playlistId, false);
        } else {
            throw new TokenExpiredException("Token expired");
        }
    }

    @Override
    public Tracks deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            trackDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return trackDAO.getTracksInPlaylist(playlistId, true);
        } else {
            throw new TokenExpiredException("Token expired");
        }
    }

    @Override
    public Tracks addTrackToPlaylist(Track track, int playlistId, String token) throws TokenExpiredException {
        boolean tokenExpired = tokenDAO.checkIfTokenIsValid(token);

        if (!tokenExpired) {
            trackDAO.addTrackToPlaylist(track, playlistId);
            return trackDAO.getTracksInPlaylist(playlistId, true);
        } else {
            throw new TokenExpiredException("Token expired");
        }
    }
}
