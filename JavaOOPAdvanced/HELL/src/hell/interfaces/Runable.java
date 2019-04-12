package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Runable {
    void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
