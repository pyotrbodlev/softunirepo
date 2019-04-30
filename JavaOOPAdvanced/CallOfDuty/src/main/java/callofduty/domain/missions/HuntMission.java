package callofduty.domain.missions;


import callofduty.constants.GlobalConstants;

public class HuntMission extends BaseMission {
    private static final Integer HUNT_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX = 150;

    private static final Integer HUNT_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX = 200;

    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating((rating * HUNT_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX)
                / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty((bounty * HUNT_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX)
                / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }
}
