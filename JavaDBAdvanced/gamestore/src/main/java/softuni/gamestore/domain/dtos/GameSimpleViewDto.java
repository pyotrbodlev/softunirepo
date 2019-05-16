package softuni.gamestore.domain.dtos;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class GameSimpleViewDto {
    private String title;
    private BigDecimal price;

    public GameSimpleViewDto() {
    }

    public GameSimpleViewDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
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
}
