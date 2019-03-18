package callofduty.controller;

import callofduty.constants.Text;
import callofduty.domain.agents.Master;
import callofduty.domain.agents.Novice;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    private Map<String, Agent> registeredAgents;
    private Map<String, Mission> registeredMission;
    private MissionControl missionControl;

    public MissionManagerImpl(MissionControl missionControl) {
        this.registeredAgents = new LinkedHashMap<>();
        this.registeredMission = new LinkedHashMap<>();
        this.missionControl = missionControl;
    }

    @Override
    public String agent(List<String> arguments) {
        String id = arguments.get(1);
        String name = arguments.get(2);

        Agent agent = new Novice(id, name);
        this.registeredAgents.put(id, agent);

        return String.format(Text.REGISTERED_AGENT, agent.getName(), agent.getId());
    }

    @Override
    public String request(List<String> arguments) {
        //agentId (string), missionId (string), missionRating (double), missionBounty (double).
        String agentId = arguments.get(1);
        String missionId = arguments.get(2);
        double missionRating = Double.parseDouble(arguments.get(3));
        double missionBounty = Double.parseDouble(arguments.get(4));

        Mission mission = this.missionControl.generateMission(missionId, missionRating, missionBounty);
        this.registeredMission.put(missionId, mission);

        this.registeredAgents.get(agentId).acceptMission(mission);
        String missionType = mission.getClass().getSimpleName();
        String missionResultType = missionType.substring(0, missionType.indexOf("M"));

        return String.format(Text.GENERATE_MISSION, missionResultType, mission.getId(), this.registeredAgents.get(agentId).getName());
    }

    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(1);
        Agent currentAgent = this.registeredAgents.get(agentId);
        currentAgent.completeMissions();

        if (checkAgentStatus(currentAgent)) {
            updateCurrentAgent(currentAgent);
        }

        return String.format(Text.MISSIONS_COMPLETED, currentAgent.getName(), currentAgent.getId());
    }

    private void updateCurrentAgent(Agent currentAgent) {
        if (currentAgent.getClass().getSimpleName().equals(Novice.class.getSimpleName())) {
            Agent newAgent = new Master(currentAgent.getId(), currentAgent.getName(), currentAgent.getRating());
            Injector.inject(currentAgent, newAgent);
            this.registeredAgents.remove(currentAgent.getId());
            this.registeredAgents.put(newAgent.getId(), newAgent);
        }
    }

    private boolean checkAgentStatus(Agent currentAgent) {
        try {
            Field completedMissions = currentAgent.getClass().getSuperclass().getDeclaredField("completedMissions");
            completedMissions.setAccessible(true);
            ArrayDeque<?> list = (ArrayDeque<?>) completedMissions.get(currentAgent);
            return list.size() >= 3;
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return false;
        }
    }

    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(1);

        for (Map.Entry<String, Agent> stringAgentEntry : registeredAgents.entrySet()) {
            if (id.equals(stringAgentEntry.getKey())) {
                return stringAgentEntry.getValue().toString();
            }
        }

        for (Map.Entry<String, Mission> stringMissionEntry : registeredMission.entrySet()) {
            if (id.equals(stringMissionEntry.getKey())) {
                return printMission(stringMissionEntry.getValue());
            }
        }

        return null;
    }

    private String printMission(Mission mission) {
        return String.format("%s Mission - %s", mission.getClass().getSimpleName().substring(0, mission.getClass().getSimpleName().indexOf("M")), mission.getId()) + System.lineSeparator() +
                String.format("Status: %s", checkMissionStatus(mission) ? "Open" : "Completed") + System.lineSeparator() +
                String.format("Rating: %.2f", mission.getRating()) + System.lineSeparator() +
                String.format("Bounty: %.2f", mission.getBounty());
    }

    private boolean checkMissionStatus(Mission mission) {
        for (Agent agent : registeredAgents.values()) {
            try {
                Field completedMissions = agent.getClass().getSuperclass().getDeclaredField("completedMissions");
                completedMissions.setAccessible(true);
                ArrayDeque<?> listCompletedMissions = (ArrayDeque<?>) completedMissions.get(agent);
                if (listCompletedMissions.contains(mission)) {
                    return false;
                }
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                ;
            }
        }
        return true;
    }

    @Override
    public String over(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        //Novice Agents: {noviceAgentsCount}
        //Master Agents: {masterAgentsCount}
        //Assigned Missions: {totalAssignedMissionsCount}
        //Completed Missions: {totalCompletedMissionsCount}
        //Total Rating Given: {totalRatingEarned}
        //Total Bounty Given: ${totalBountyEarned}

        sb.append(String.format("Novice Agents: %d", this.registeredAgents.values().stream().filter(a -> a.getClass().getSimpleName().equals("Novice")).count())).append(System.lineSeparator())
                .append(String.format("Master Agents: %d", this.registeredAgents.values().stream().filter(a -> a.getClass().getSimpleName().equals("Master")).count())).append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.registeredMission.size())).append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", getCompletedMissionsCount())).append(System.lineSeparator())
                .append(String.format("Total Rating Given: %.2f", getTotalRating())).append(System.lineSeparator())
                .append(String.format("Total Bounty Given: $%.2f", getTotalBounty())).append(System.lineSeparator());

        return sb.toString();
    }

    private double getTotalBounty() {
        double bounty = 0;

        for (Agent agent : registeredAgents.values()) {
            try {
                Double bounty1 = ((Master) agent).getBounty();
                bounty += bounty1;
            } catch (ClassCastException ignored) {
                ;
            }
        }
        return bounty;
    }

    private double getTotalRating() {
        double rating = 0;

        for (Agent agent : registeredAgents.values()) {
            rating += agent.getRating();
        }

        return rating;
    }

    private int getCompletedMissionsCount() {
        int count = 0;

        for (Agent agent : registeredAgents.values()) {
            try {
                Field completedMissions = agent.getClass().getSuperclass().getDeclaredField("completedMissions");
                completedMissions.setAccessible(true);
                ArrayDeque<?> listCompletedMissions = (ArrayDeque<?>) completedMissions.get(agent);
                count += listCompletedMissions.size();
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                ;
            }
        }

        return count;
    }
}
