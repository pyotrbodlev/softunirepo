package alararestaurant.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdersImportXml {

    @XmlElement(name = "order")
    private List<OrderImportXmlDto> orders;

    public OrdersImportXml() {
    }

    public List<OrderImportXmlDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderImportXmlDto> orders) {
        this.orders = orders;
    }
}
