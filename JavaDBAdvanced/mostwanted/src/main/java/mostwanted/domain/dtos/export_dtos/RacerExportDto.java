package mostwanted.domain.dtos.export_dtos;

import java.util.List;

public class RacerExportDto {
    private String name;
    private List<CarExportDto> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarExportDto> getCars() {
        return cars;
    }

    public void setCars(List<CarExportDto> cars) {
        this.cars = cars;
    }
}
