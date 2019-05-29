package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "racers")
public class Racer extends BaseEntity {
    private String name;
    private int age;
    private BigDecimal bounty;
    private Town homeTown;
    private List<Car> cars;

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "bounty")
    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_town_id", referencedColumnName = "id")
    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @OneToMany(mappedBy = "racer", targetEntity = Car.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
