package nl.han.oose.service;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.tracks.Tracks;

public interface TrackService {
    Tracks getAllTrackInPlaylist(int playlistId, String token) throws TokenExpiredException;
}
