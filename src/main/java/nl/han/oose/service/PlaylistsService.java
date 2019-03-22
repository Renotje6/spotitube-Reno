package nl.han.oose.service;

import nl.han.oose.entity.playlists.Playlists;

public interface PlaylistsService {
    Playlists getAllPlaylists(String token);
}
