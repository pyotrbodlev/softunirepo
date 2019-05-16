package softuni.gamestore.domain.dtos;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDetailedViewDto {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDetailedViewDto() {
    }

    public GameDetailedViewDto(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @NotNull
    @Pattern(regexp = "[A-Z].*", message = "Title must start with capital letter.")
    @Size(min = 3, max = 100, message = "Title length must be between 3 and 100 symbols.")
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
