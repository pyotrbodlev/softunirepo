package callofduty.domain.agents;

import callofduty.domain.BaseModel;
import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent extends BaseModel implements Agent {
    private static final Double AGENT_BASE_RATING = 0D;

    private String name;

    private Double rating;

    private List<Mission> assignedMissions;

    private List<Mission> completedMissions;

    protected BaseAgent(String id, String name) {
        super(id);
        this.setName(name);
        this.setRating(AGENT_BASE_RATING);
        this.assignedMissions = new ArrayList<>();
        this.completedMissions = new ArrayList<>();
    }

    protected void achieveBonuses() {
        for (Mission acceptedMission : this.assignedMissions) {
            this.setRating(this.getRating() + acceptedMission.getRating());
        }
    }

    protected void moveAcceptedMissionsToCompletedMissions() {
        this.completedMissions.addAll(assignedMissions);
        this.assignedMissions.clear();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return this.rating;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }

    protected List<Mission> getAssignedMissions() {
        return this.assignedMissions;
    }

    public void acceptMission(Mission mission) {
        this.assignedMissions.add(mission);
    }

    public void completeMissions() {
        this.achieveBonuses();
        this.moveAcceptedMissionsToCompletedMissions();
    }

    @Override
    public String toString() {
        return
                this
                        .getClass()
                        .getSimpleName()
                        .replace("Agent", " Agent - ")
                        + this.getName()
                        + System.lineSeparator()
                        + "Personal Code: "
                        + this.getId()
                        + System.lineSeparator()
                        + "Assigned Missions: "
                        + this.assignedMissions.size()
                        + System.lineSeparator()
                        + "Completed Missions: "
                        + this.completedMissions.size()
                        + System.lineSeparator()
                        + String.format("Rating: %.2f", this.getRating());
    }
}
