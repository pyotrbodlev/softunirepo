package callofduty.domain.missions;


import callofduty.constants.GlobalConstants;

public class SurveillanceMission extends BaseMission {
    private static final Integer SURVEILLANCE_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX = 25;

    private static final Integer SURVEILLANCE_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX = 150;

    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating((rating * SURVEILLANCE_MISSION_RATING_PERCENTAGE_MODIFYING_INDEX)
                / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty((bounty * SURVEILLANCE_MISSION_BOUNTY_PERCENTAGE_MODIFYING_INDEX) / GlobalConstants.TOTAL_PERCENTAGE_INDEX);
    }
}
