package cresla.factories;

import cresla.interfaces.Reactor;
import cresla.interfaces.ReactorFactory;

import java.lang.reflect.InvocationTargetException;

public class ReactorFactoryImpl implements ReactorFactory {

    private static final String REACTORS_PATH = "cresla.entities.reactors.";

    @Override
    public Reactor create(String type, int additionalParameter, int moduleCapacity) {

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
