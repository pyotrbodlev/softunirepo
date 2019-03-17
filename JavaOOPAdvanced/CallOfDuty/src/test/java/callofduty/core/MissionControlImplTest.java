package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Test;

public class MissionControlImplTest {

    private MissionControl missionControl;

    @org.junit.Before
    public void setUp() throws Exception {
        this.missionControl = new MissionControlImpl();
    }

    @org.junit.Test
    public void generateMission() throws NoSuchFieldException {
        Mission first_mission = missionControl.generateMission("First Mission", 10d, 1000001D);
        Mission second_mission = missionControl.generateMission("Second Mission", 12d, 1000001D);
        Mission third_mission = missionControl.generateMission("Third Mission", 12d, 12d);
        Mission fourth_mission = missionControl.generateMission("Fourth Mission", 12d, 12d);

        Assert.assertEquals("EscortMission", fourth_mission.getClass().getSimpleName());
    }

    @Test
    public void testCreateMissionWithMoreBounty(){
        Mission first_mission = missionControl.generateMission("First Mission", 10d, Double.MAX_VALUE);

        Assert.assertEquals(125000D, first_mission.getBounty(), 0.1);
    }

    @Test
    public void testCreateMissionWithMoreRating(){
        Mission first_mission = missionControl.generateMission("First Mission", Double.MAX_VALUE, Double.MAX_VALUE);

        Assert.assertEquals(75D, first_mission.getRating(), 0.1);
    }

}