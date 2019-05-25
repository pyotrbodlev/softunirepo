package softuni.cardealer.domain.dtos;

import softuni.cardealer.domain.entites.Customer;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWithCustomersDto {

    @XmlElement(name = "customer")
    @XmlElementWrapper(name = "customers")
    private List<CustomerXmlDto> customers;

    public ListWithCustomersDto() {
    }

    public ListWithCustomersDto(List<CustomerXmlDto> customers) {
        this.customers = customers;
    }

    public List<CustomerXmlDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerXmlDto> customers) {
        this.customers = customers;
    }
}
