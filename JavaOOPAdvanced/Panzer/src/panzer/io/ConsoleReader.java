package panzer.io;

import panzer.contracts.InputReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader implements InputReader {
    private BufferedReader reader;

    public ConsoleReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readLine() throws IOException {
        return this.reader.readLine();
    }
}
