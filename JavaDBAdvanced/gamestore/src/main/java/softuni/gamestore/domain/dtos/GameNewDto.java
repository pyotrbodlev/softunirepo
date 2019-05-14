package softuni.gamestore.domain.dtos;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

public class GameNewDto {
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameNewDto() {
    }

    public GameNewDto(String title, BigDecimal price, double size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @NotNull
    @Pattern(regexp = "[A-Z].*")
    @Size(min = 3, max = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Positive(message = "Price must be positive number")
    @Digits(integer = 19, fraction = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    @Positive(message = "Size must be positive number")
    @Digits(integer = 10, fraction = 1)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(http:\\/\\/|https:\\/\\/).+")
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
