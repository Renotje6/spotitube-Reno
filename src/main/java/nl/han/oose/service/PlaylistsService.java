package nl.han.oose.service;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.playlists.Playlists;

public interface PlaylistsService {
    Playlists getAllPlaylists(String token) throws TokenExpiredException;

    Playlists deletePlaylist(int playlistId, String token) throws TokenExpiredException;

    Playlists addPlaylist(Playlist playlistToAdd, String token) throws TokenExpiredException;

    Playlists editPlaylist(Playlist playlist, int playlistId, String token) throws TokenExpiredException;
}
