package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District extends BaseEntity {
    private String name;
    private Town town;

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
