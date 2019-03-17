package callofduty.entities.missions;

import callofduty.controller.MissionManagerImpl;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionManager;

import java.lang.reflect.Field;

public abstract class AbstractMission implements Mission {
    private String id;
    private Double rating;
    private Double bounty;

    public AbstractMission(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

}
