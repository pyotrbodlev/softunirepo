package callofduty;

import callofduty.controller.MissionManagerImpl;
import callofduty.core.MissionControlImpl;
import callofduty.engines.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InputReader reader = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
        OutputWriter writer = new ConsoleWriter();
        MissionManager missionManager = new MissionManagerImpl(new MissionControlImpl());
        Engine engine = new Engine(missionManager, reader, writer);

        engine.run();
    }
}




