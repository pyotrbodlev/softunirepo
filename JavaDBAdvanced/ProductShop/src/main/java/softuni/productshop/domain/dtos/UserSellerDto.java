package softuni.productshop.domain.dtos;

import softuni.productshop.domain.entities.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserSellerDto {
    private String firstName;
    private String lastName;
    private List<ProductWithBuyerDto> soldProducts;

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductWithBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductWithBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
