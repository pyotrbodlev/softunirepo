package callofduty.io;

import callofduty.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader implements InputReader {
    private BufferedReader reader;

    public ConsoleReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (IOException ignored) {
            ;
        }

        return null;
    }
}
