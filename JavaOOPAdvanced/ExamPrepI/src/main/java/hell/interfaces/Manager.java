package hell.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Manager {
    String heroCommand(List<String> args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    String itemCommand(List<String> args);

    String recipeCommand(List<String> args);

    String inspectCommand(List<String> args) throws IllegalAccessException;

    String quitCommand(List<String> args);
}
