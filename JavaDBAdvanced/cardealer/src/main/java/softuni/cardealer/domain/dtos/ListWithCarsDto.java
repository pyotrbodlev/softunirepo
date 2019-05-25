package softuni.cardealer.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWithCarsDto {

    @XmlElement(name = "car")
    private List<CarXmlDto> cars;

    public ListWithCarsDto() {
    }

    public ListWithCarsDto(List<CarXmlDto> cars) {
        this.cars = cars;
    }

    public List<CarXmlDto> getCars() {
        return cars;
    }

    public void setCars(List<CarXmlDto> cars) {
        this.cars = cars;
    }
}
