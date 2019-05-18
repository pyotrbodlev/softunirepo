package softuni.productshop.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategorySeedDto {
    private String name;

    public CategorySeedDto() {
    }

    public CategorySeedDto(String name) {
        this.name = name;
    }

    @NotNull(message = "Category name cannot be null")
    @Size(min = 3, max = 15, message = "Name length must be between 3 and 15 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
