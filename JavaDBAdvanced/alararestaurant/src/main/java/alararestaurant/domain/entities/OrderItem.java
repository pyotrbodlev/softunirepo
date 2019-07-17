package alararestaurant.domain.entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "quantity")}, name = "order_items")
@Check(constraints = "quantity > 0")
public class OrderItem extends BaseEntity {
    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem() {
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
