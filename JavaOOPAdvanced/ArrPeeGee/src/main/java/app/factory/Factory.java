package app.factory;

import app.contracts.Targetable;
import app.contracts.TargetableFactory;

import java.lang.reflect.InvocationTargetException;

public class Factory implements TargetableFactory {

    private static final String HEROES_PATH = "app.models.participants.";

    public Targetable create(String name, String type) {

        Targetable hero = null;

        try {
            hero = (Targetable) Class.forName(HEROES_PATH + type).getConstructor(String.class).newInstance(name);
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException ignored) {
           ;
        }

        return hero;
    }
}
