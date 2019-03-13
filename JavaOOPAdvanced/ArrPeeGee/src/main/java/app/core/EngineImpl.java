package app.core;

import app.contracts.Battlefield;
import app.contracts.Reader;
import app.contracts.Writer;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements app.contracts.Engine {

    private Reader reader;
    private Writer writer;
    private Battlefield battleField;

    public EngineImpl(Reader reader, Writer writer, Battlefield battlefield) {
        this.reader = reader;
        this.writer = writer;
        this.battleField = battlefield;
    }

    public void run() throws IOException {
        String line = reader.readLine();

        while (!line.equals("Peace")) {
            String[] lineTokens = line.split("\\s+");

            switch (lineTokens[0].toLowerCase()) {
                case "createparticipant":
                    battleField.createParticipant(lineTokens[1], lineTokens[2]);
                    break;
                case "createaction":
                    battleField.createAction(lineTokens[1],
                            Arrays.copyOf(Arrays.stream(lineTokens).skip(2).toArray(),
                                    Arrays.stream(lineTokens).skip(2).toArray().length,
                                    String[].class));
                    break;
                case "statparticipants":
                    battleField.reportParticipants();
                    break;
                case "statactions":
                    battleField.reportActions();
                    break;
                default:
                    this.writer.writeLine("Invalid command");
                    break;
            }

            line = reader.readLine();
        }
    }
}
