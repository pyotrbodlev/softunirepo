package cresla;

import cresla.controller.ProgramManager;
import cresla.core.Engine;
import cresla.factories.ModuleFactoryImpl;
import cresla.factories.ReactorFactoryImpl;
import cresla.interfaces.*;
import cresla.io.ConsoleReader;
import cresla.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();
        ModuleFactory moduleFactory = new ModuleFactoryImpl();
        ReactorFactory reactorFactory = new ReactorFactoryImpl();
        Manager manager = new ProgramManager(moduleFactory, reactorFactory);
        Engine engine = new Engine(reader, writer, manager);

        engine.run();

    }
}
