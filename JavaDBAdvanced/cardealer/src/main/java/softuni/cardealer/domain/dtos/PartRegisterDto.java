package softuni.cardealer.domain.dtos;

import softuni.cardealer.domain.entites.Supplier;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PartRegisterDto {
    private String name;
    private BigDecimal price;
    private int quantity;
    private Supplier supplier;

    public PartRegisterDto() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
