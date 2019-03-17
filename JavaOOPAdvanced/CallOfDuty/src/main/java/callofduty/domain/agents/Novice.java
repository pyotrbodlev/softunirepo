package callofduty.domain.agents;

public class Novice extends AbstractAgent {
    private static final double AGENT_START_RATING = 0;

    public Novice(String id, String name) {
        super(id, name, AGENT_START_RATING);
    }

}
