package cresla;

import cresla.controller.ProgramManager;
import cresla.core.Engine;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.ConsoleReader;
import cresla.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();
        Manager manager = new ProgramManager();
        Engine engine = new Engine(reader, writer, manager);

        engine.run();

    }
}
