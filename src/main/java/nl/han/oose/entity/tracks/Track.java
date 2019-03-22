package nl.han.oose.entity.tracks;

public class Track {
    private int id;
    private String performer;
    private String title;
    private String url;
    private int duration;
    private boolean offlineAvailable;

    public Track(){}

    public Track(int id, String performer, String title, String url, int duration, boolean offlineAvailable) {
        this.id = id;
        this.performer = performer;
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
