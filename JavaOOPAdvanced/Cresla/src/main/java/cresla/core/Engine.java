package cresla.core;

import cresla.interfaces.Executable;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Engine implements Executable {
    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;

    public Engine(InputReader reader, OutputWriter writer, Manager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            String line = reader.readLine();

            if ("Exit".equals(line)) {
                this.writer.writeLine(manager.exitCommand(Arrays.asList(line.split(" "))));
                break;
            }

            List<String> args = Arrays.asList(line.split(" "));

            String result = interpredCommand(args.get(0), args);

            this.writer.writeLine(result);
        }
    }

    private String interpredCommand(String command, List<String> args) {
        try {
            return (String) Manager.class.getMethod(command.toLowerCase() + "Command", List.class).invoke(this.manager, args);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
