package softuni.productshop.domain.dtos;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {
    private String name;
    private BigDecimal price;

    public ProductSeedDto() {
    }

    public ProductSeedDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @NotNull(message = "Product cannot be null.")
    @Size(min = 3, message = "Procut's name length must be at least 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Product must have price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
