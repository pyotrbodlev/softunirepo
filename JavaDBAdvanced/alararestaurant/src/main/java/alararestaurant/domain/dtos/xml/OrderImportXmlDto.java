package alararestaurant.domain.dtos.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportXmlDto {

    @XmlElement(name = "customer")
    @NotNull
    private String customer;

    @XmlElement(name = "employee")
    @NotNull
    private String employee;

    @XmlElement(name = "date-time")
    @NotNull
    private String dateTime;

    @XmlElement(name = "type")
    private String type;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OrderItemImportDto> orderItems;

    public OrderImportXmlDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemImportDto> getItems() {
        return orderItems;
    }

    public void setItems(List<OrderItemImportDto> orderItems) {
        this.orderItems = orderItems;
    }
}
