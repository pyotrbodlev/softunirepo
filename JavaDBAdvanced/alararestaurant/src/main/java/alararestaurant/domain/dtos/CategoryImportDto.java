package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryImportDto {
    @Expose
    private String name;

    public CategoryImportDto() {
    }

    @Size(min = 3, max = 30)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
