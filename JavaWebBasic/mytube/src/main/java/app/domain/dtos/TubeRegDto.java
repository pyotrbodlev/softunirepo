package app.domain.dtos;

public class TubeRegDto {
    private String title;
    private String description;
    private String youtubeLink;
    private String uploader;

    public TubeRegDto(String title, String description, String youtubeLink, String uploader) {
        this.title = title;
        this.description = description;
        this.youtubeLink = youtubeLink;
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getUploader() {
        return uploader;
    }
}
