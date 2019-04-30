package fbdb.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition {
    private int id;
    private String name;
    private CompetitionType type;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        if (!type.getName().equals("local") &&
                !type.getName().equals("national") &&
                !type.getName().equals("international")) {
            throw new IllegalArgumentException("Wrong type");
        }

        this.type = type;
    }
}
