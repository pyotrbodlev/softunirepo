package io;

import interfaces.Outputable;

public class ConsoleWriter implements Outputable {

    @Override
    public void writeLine(String line){
        System.out.println(line);
    }

    @Override
    public void write(String line){
        System.out.print(line);
    }
}
