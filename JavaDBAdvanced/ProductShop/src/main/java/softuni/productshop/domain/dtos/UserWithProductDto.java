package softuni.productshop.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductDto {
    private String firstName;
    private String lastName;
    private int age;

    @XmlElement(name = "count")
    private int soldProductsCount;

    @XmlElement(name = "product")
    @XmlElementWrapper(name = "sold-products")
    private List<ProductSeedDto> soldProducts;

    public UserWithProductDto() {
    }

    public UserWithProductDto(String firstName, String lastName, int age, List<ProductSeedDto> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ProductSeedDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSeedDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public int getSoldProductsCount() {
        return soldProductsCount;
    }

    public void setSoldProductsCount(int soldProductsCount) {
        this.soldProductsCount = soldProductsCount;
    }
}
