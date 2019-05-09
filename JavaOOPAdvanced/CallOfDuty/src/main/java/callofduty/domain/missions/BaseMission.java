package callofduty.domain.missions;

import callofduty.domain.BaseModel;
import callofduty.interfaces.Mission;

public abstract class BaseMission extends BaseModel implements Mission {
    private Double rating;

    private Double bounty;

    protected BaseMission(String id, Double rating, Double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    protected void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    @Override
    public String toString() {
        return
                this
                        .getClass()
                        .getSimpleName()
                        .replace("Mission", " Mission - ")
                        + this.getId()
                        + System.lineSeparator()
                        + "Status: {missionStatus}"
                        + System.lineSeparator()
                        + String.format("Rating: %.2f", this.getRating())
                        + System.lineSeparator()
                        + String.format("Bounty: %.2f", this.getBounty());
    }
}
