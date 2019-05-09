package callofduty.controller;

import callofduty.interfaces.Agent;

import java.lang.reflect.Field;

public class Injector {

    public static void inject(Agent currentAgent, Agent newAgent) {
        try {
            Field openedMissions = currentAgent.getClass().getSuperclass().getDeclaredField("assignedMissions");
            Field openedMissions1 = newAgent.getClass().getSuperclass().getDeclaredField("assignedMissions");
            openedMissions1.setAccessible(true);
            openedMissions.setAccessible(true);
            openedMissions1.set(newAgent, openedMissions.get(currentAgent));

            Field completedMissions = currentAgent.getClass().getSuperclass().getDeclaredField("completedMissions");
            Field completedMissions1 = newAgent.getClass().getSuperclass().getDeclaredField("completedMissions");
            completedMissions1.setAccessible(true);
            completedMissions.setAccessible(true);
            completedMissions1.set(newAgent, completedMissions.get(currentAgent));
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            ;
        }
    }
}
