package io;

import interfaces.Readable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Readable {
    private BufferedReader reader;

    public ConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (IOException ioe) {
            return null;
        }
    }
}
