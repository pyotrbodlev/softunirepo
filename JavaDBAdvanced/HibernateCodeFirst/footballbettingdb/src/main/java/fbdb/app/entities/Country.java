package fbdb.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    private String id;
    private int name;
    private Continent continent;

    @Id
    @Column(name = "id", length = 3, columnDefinition = "CHAR(3)")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "continent_id", referencedColumnName = "id")
    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
