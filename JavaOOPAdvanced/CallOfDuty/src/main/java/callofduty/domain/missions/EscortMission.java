package callofduty.domain.missions;

import callofduty.constants.GlobalConstants;

public class EscortMission extends BaseMission {
    private static final Integer ESCORT_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX = 75;

    private static final Integer ESCORT_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX = 125;

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating((rating * ESCORT_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX) / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty((bounty * ESCORT_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX) / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }
}
