package cresla.factories;

import cresla.interfaces.Factory;
import cresla.interfaces.Reactor;

import java.lang.reflect.InvocationTargetException;

public class ReactorFactory {

    private static final String REACTORS_PATH = "cresla.entities.reactors.";

    public static Reactor create(String type, int additionalParameter, int moduleCapacity) {

        Reactor reactor = null;

        try {
            reactor = (Reactor) Class.forName(REACTORS_PATH + type + "Reactor")
                    .getConstructor(int.class, int.class)
                    .newInstance(additionalParameter, moduleCapacity);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ignored) {
            ;
        }

        return reactor;
    }
}
