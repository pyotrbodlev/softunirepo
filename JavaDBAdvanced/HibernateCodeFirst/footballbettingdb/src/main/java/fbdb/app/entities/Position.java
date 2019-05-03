package fbdb.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    private String id;
    private int description;

    @Id
    @Column(name = "id", columnDefinition = "CHAR(2)", unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(length = 15)
    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
