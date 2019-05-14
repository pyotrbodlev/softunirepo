package softuni.gamestore.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class GameDeleteDto {
    private String title;

    public GameDeleteDto() {
    }

    public GameDeleteDto(String title) {
        this.title = title;
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
}
