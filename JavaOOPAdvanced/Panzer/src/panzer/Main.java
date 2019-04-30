package panzer;

import panzer.contracts.InputReader;
import panzer.contracts.Manager;
import panzer.contracts.OutputWriter;
import panzer.core.Engine;
import panzer.core.PanzerManager;
import panzer.io.ConsoleReader;
import panzer.io.ConsoleWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        InputReader reader = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
        OutputWriter writer = new ConsoleWriter();
        Manager manager = new PanzerManager();
        Engine engine = new Engine(reader, writer, manager);

        engine.run();
    }
}
