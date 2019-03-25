package nl.han.oose.service.trackService;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Track;
import nl.han.oose.entity.tracks.Tracks;

public interface TrackService {
    Tracks getAllTracksInPlaylist(int playlistId, String token) throws TokenExpiredException;

    Tracks getAllTracksNotInPlaylist(int playlistId, String token) throws TokenExpiredException;

    Tracks deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException;

    Tracks addTrackToPlaylist(Track track, int playlistId, String token) throws TokenExpiredException;
}
