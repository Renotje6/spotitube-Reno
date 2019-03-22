package nl.han.oose.entity.tracks;

public class Song extends Track{
    private String album;

    public Song(int id, String performer, String title, String url, int duration, boolean offlineAvailable, String album) {
        super(id, performer, title, url, duration, offlineAvailable);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
