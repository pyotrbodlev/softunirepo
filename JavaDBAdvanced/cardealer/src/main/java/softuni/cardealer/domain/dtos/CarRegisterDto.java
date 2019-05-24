package softuni.cardealer.domain.dtos;

import softuni.cardealer.domain.entites.Part;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CarRegisterDto {
    private String make;
    private String model;
    private long travelledDistance;
    private List<Part> parts;

    public CarRegisterDto() {
    }

    @NotNull
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotNull
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
