package softuni.cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWithSalesDto {
    @XmlElement(name = "sale")
    private List<SaleXmlDto> sales;

    public ListWithSalesDto() {
    }

    public ListWithSalesDto(List<SaleXmlDto> sales) {
        this.sales = sales;
    }

    public List<SaleXmlDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleXmlDto> sales) {
        this.sales = sales;
    }
}
