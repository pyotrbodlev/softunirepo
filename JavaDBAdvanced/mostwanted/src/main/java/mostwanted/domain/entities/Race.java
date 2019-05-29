package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {
    private int laps;
    private District district;
    private List<RaceEntry> entries;

    @Column(name = "laps", nullable = false)
    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @ManyToOne(targetEntity = District.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race", targetEntity = RaceEntry.class)
    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
