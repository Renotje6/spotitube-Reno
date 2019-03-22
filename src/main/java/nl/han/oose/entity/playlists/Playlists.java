package nl.han.oose.entity.playlists;

import java.util.List;

public class Playlists {
    private List<Playlist> playlists;
    private int length;


    public Playlists() {
    }

    public Playlists(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
