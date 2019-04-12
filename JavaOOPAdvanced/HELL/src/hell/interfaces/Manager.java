package hell.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Manager {
    String addHero(List<String> args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    String addItem(List<String> args);

    String addRecipe(List<String> args);

    String inspect(List<String> args) throws IllegalAccessException;

    String quit(List<String> args);
}
