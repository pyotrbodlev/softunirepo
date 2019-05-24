package softuni.cardealer.domain.dtos;

import javax.validation.constraints.NotNull;

public class SupplierRegisterDto {
    private String name;
    private boolean isImporter;

    public SupplierRegisterDto() {
    }

    public SupplierRegisterDto(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
