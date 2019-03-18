package callofduty.engines;

import callofduty.controller.MissionManagerImpl;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Engine {
    private MissionManager missionManager;
    private InputReader reader;
    private OutputWriter writer;

    public Engine(MissionManager missionManager, InputReader reader, OutputWriter writer) {
        this.missionManager = missionManager;
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        while (true){
            String line = reader.readLine();

            if ("Over".equals(line)){
                this.writer.println(this.missionManager.over(Arrays.asList(line.split(" "))));
                break;
            }

            List<String> args = Arrays.asList(line.split(" "));
            String command = args.get(0).toLowerCase();

            String result = (String) MissionManagerImpl.class.getMethod(command, List.class).invoke(this.missionManager, args);

            this.writer.println(result);
        }
    }
}
