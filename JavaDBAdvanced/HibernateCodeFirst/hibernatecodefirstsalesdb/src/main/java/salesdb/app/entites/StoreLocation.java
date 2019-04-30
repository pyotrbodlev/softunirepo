package salesdb.app.entites;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    private int id;
    private String locationName;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "product", targetEntity = Sale.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
