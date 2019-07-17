package alararestaurant.domain.entities;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "price")}, name = "items")
@Check(constraints = "price > 0")
public class Item extends BaseEntity {
    private String name;
    private Category category;
    private BigDecimal price;
    private List<OrderItem> orderItems;

    public Item() {
    }

    @Size(min = 3, max = 30)
    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "item", targetEntity = OrderItem.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
