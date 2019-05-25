package softuni.cardealer.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarXmlDto {
    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private long travelledDistance;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartXmlDto> parts;

    public CarXmlDto() {
    }

    public CarXmlDto(String make, String model, long travelledDistance, List<PartXmlDto> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartXmlDto> getParts() {
        return parts;
    }

    public void setParts(List<PartXmlDto> parts) {
        this.parts = parts;
    }
}
