package softuni.productshop.domain.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductSimpleViewDto {
    private String name;
    private BigDecimal price;
    private String sellerFullName;

    public ProductSimpleViewDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }

}
