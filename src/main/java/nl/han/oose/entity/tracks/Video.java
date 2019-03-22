package nl.han.oose.entity.tracks;

public class Video extends Track{
    private String publicationDate;
    private String description;

    public Video(int id, String performer, String title, String url, int duration, boolean offlineAvailable, String publicationDate, String description) {
        super(id, performer, title, url, duration, offlineAvailable);
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}