package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

public class Master extends AbstractAgent implements BountyAgent {
    private double bounty;

    public Master(String id, String name, double rating) {
        super(id, name, rating);
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public void completeMissions() {
        while (!super.getOpenedMissions().isEmpty()) {
            Mission currentMission = this.getOpenedMissions().pop();
            setRating(currentMission.getRating());
            setBounty(currentMission.getBounty());
            this.getCompletedMissions().push(currentMission);
        }
    }

    private void setBounty(double bounty) {
        this.bounty += bounty;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() +
                String.format("Bounty Earned: $%.2f", this.getBounty());
    }
}
