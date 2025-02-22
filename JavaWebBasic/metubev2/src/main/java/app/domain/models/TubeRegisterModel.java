package app.domain.models;

public class TubeRegisterModel {
    private String title;
    private String author;
    private String description;
    private String youtubeId;

    public TubeRegisterModel() {
    }

    public TubeRegisterModel(String title, String author, String description, String youtubeId) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.youtubeId = youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }
}
