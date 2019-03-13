package hell.core;

import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;
import hell.interfaces.Runable;
import hell.manager.ProgramManager;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runable {
    private OutputWriter writer;
    private InputReader reader;
    private Manager manager;

    public Engine(OutputWriter writer, InputReader reader, Manager manager) {
        this.writer = writer;
        this.reader = reader;
        this.manager = manager;
    }

    @Override
    public void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        while (true){
            String line = reader.readLine();

            String command = line.split(" ")[0].toLowerCase().charAt(0) + line.split(" ")[0].toLowerCase().substring(1) + "Command";

            List<String> args = Arrays.asList(line.split(" "));
            String result = (String) ProgramManager.class.getMethod(command, List.class).invoke(this.manager, args);

            this.writer.writeLine(result);

            if ("Quit".equals(line)){
                break;
            }
        }

    }
}
