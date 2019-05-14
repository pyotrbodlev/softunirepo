package softuni.gamestore.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

public class GameEditDto {
    private String title;
    private Map<String, String> params;

    public GameEditDto() {
    }

    public GameEditDto(String title, Map<String, String> params) {
        this.title = title;
        this.params = params;
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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
