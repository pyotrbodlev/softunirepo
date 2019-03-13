package app.factory;

import app.contracts.Action;
import app.contracts.ActionFactory;

import java.lang.reflect.InvocationTargetException;

public class ActionFactoryImpl implements ActionFactory {
    private static final String ACTIONS_PATH = "app.models.actions.";

    @Override
    public Action create(String actionName, String... participantNames) {

        Action action = null;
        try {
            action = (Action) Class.forName(ACTIONS_PATH + actionName).getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ignored) {
           ;
        }

        return action;
    }
}
