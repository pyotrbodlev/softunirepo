package callofduty.domain.agents;

import callofduty.annotations.Inject;
import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.util.ArrayDeque;

public abstract class AbstractAgent implements Agent {

    private String id;
    private String name;
    private double rating;

    @Inject
    private ArrayDeque<Mission> openedMissions;

    @Inject
    private ArrayDeque<Mission> completedMissions;

    protected AbstractAgent(String id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.openedMissions = new ArrayDeque<>();
        this.completedMissions = new ArrayDeque<>();
    }

    @Override
    public void acceptMission(Mission mission) {
        this.openedMissions.push(mission);
    }

    @Override
    public void completeMissions() {
        while (!this.openedMissions.isEmpty()) {
            Mission currentMission = this.openedMissions.pop();
            setRating(currentMission.getRating());
            this.completedMissions.push(currentMission);
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    protected ArrayDeque<Mission> getOpenedMissions() {
        return openedMissions;
    }

    protected ArrayDeque<Mission> getCompletedMissions() {
        return completedMissions;
    }

    protected void setRating(double rating) {
        this.rating += rating;
    }

    @Override
    public String toString() {
        return String.format("%s Agent - %s", this.getClass().getSimpleName(), this.getName()) + System.lineSeparator() +
                String.format("Personal Code: %s", this.getId()) + System.lineSeparator() +
                String.format("Assigned Missions: %d", this.getOpenedMissions().size()) + System.lineSeparator() +
                String.format("Completed Missions: %d", this.getCompletedMissions().size()) + System.lineSeparator() +
                String.format("Rating: %.2f", this.getRating());
    }
}
