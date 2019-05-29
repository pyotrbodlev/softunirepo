package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity {
    private boolean hasFinished;
    private double finishTime;
    private Car car;
    private Race race;
    private Racer racer;

    @Column(name = "has_finished")
    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    @Column(name = "finish_time")
    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    @ManyToOne(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(targetEntity = Race.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @ManyToOne(targetEntity = Racer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "racer_id", referencedColumnName = "id")
    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
