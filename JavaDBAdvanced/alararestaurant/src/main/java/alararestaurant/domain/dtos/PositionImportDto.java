package alararestaurant.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PositionImportDto {
    private String name;

    public PositionImportDto() {
    }

    @NotNull
    @Size(min = 3, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
