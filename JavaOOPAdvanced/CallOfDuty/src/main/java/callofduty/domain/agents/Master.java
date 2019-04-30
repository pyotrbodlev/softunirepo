package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.util.List;

public class Master extends BaseAgent implements BountyAgent {
    private static final Double MASTER_AGENT_BASE_BOUNTY = 0D;

    private Double bounty;

    public Master(String id, String name, Double rating) {
        super(id, name);
        this.setRating(rating);
        this.setBounty(MASTER_AGENT_BASE_BOUNTY);
    }

    @Override
    protected void achieveBonuses() {
        for (Mission acceptedMission : this.getAssignedMissions()) {
            this.setRating(this.getRating() + acceptedMission.getRating());
            this.setBounty(this.getBounty() + acceptedMission.getBounty());
        }
    }

    public Double getBounty() {
        return this.bounty;
    }

    private void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    @Override
    public String toString() {
        return
                super.toString()
                        + System.lineSeparator()
                        + String.format("Bounty Earned: $%.2f", this.getBounty());
    }
}
