package mostwanted.domain.dtos.export_dtos;

public class CarExportDto {
    private String brand;
    private String model;
    private int yearOfProduction;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return this.brand + " " + this.model + " " + this.yearOfProduction;
    }
}
