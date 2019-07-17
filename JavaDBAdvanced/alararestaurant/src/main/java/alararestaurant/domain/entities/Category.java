package alararestaurant.domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;
    private List<Item> items;

    public Category() {
    }

    @Size(min = 3, max = 30)
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", targetEntity = Item.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
