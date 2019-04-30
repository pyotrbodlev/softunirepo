package hell;

import hell.core.Engine;
import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;
import hell.manager.ProgramManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InputReader reader = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
        OutputWriter writer = new ConsoleWriter();
        Manager manager = new ProgramManager();

        Engine engine = new Engine(writer, reader, manager);

        engine.run();
    }
}