package cresla.factories;

import cresla.interfaces.Module;
import java.lang.reflect.InvocationTargetException;

public class ModuleFactory {

    private static final String MODULE_PATH = "cresla.entities.modules.";

    public static Module create(String type, int additionalParameter) {

        Module module = null;

        try {
            module = (Module) Class.forName(MODULE_PATH + type)
                    .getConstructor(int.class)
                    .newInstance(additionalParameter);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ignored) {
            ;
        }

        return module;
    }
}
