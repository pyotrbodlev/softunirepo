package panzer.core;

import panzer.constants.Commands;
import panzer.contracts.InputReader;
import panzer.contracts.Manager;
import panzer.contracts.OutputWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;

    public Engine(InputReader reader, OutputWriter writer, Manager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    public void run() throws IOException, NoSuchFieldException, IllegalAccessException {
        while (true) {
            String line = reader.readLine();
            List<String> args = Arrays.asList(line.split(" "));

            if (Commands.TERMINATE_COMMAND.equals(line)) {
                this.writer.println(this.manager.terminate(args));
                break;
            }

            switch (args.get(0)) {
                case Commands.VEHICLE_COMMAND:
                    this.writer.println(this.manager.addVehicle(args));
                    break;
                case Commands.PART_COMMAND:
                    this.writer.println(this.manager.addPart(args));
                    break;
                case Commands.BATTLE_COMMAND:
                    this.writer.println(this.manager.battle(args));
                    break;
                case Commands.INSPECT_COMMAND:
                    this.writer.println(this.manager.inspect(args));
                    break;
            }
        }
    }
}
