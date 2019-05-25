package softuni.cardealer.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWithSuppliersDto {

    @XmlElement(name = "supplier")
    private List<SupplierXmlDto> suppliers;

    public ListWithSuppliersDto() {
    }

    public ListWithSuppliersDto(List<SupplierXmlDto> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierXmlDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierXmlDto> suppliers) {
        this.suppliers = suppliers;
    }
}
