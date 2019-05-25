package softuni.cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWithCustomerSellDto {

    @XmlElement(name = "customer")
    private List<CustomerSellXmlDto> customers;

    public ListWithCustomerSellDto() {
    }

    public ListWithCustomerSellDto(List<CustomerSellXmlDto> customers) {
        this.customers = customers;
    }

    public List<CustomerSellXmlDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSellXmlDto> customers) {
        this.customers = customers;
    }
}
