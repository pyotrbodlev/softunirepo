package app;

import app.contracts.*;
import app.core.BattlefieldImplementation;
import app.core.EngineImpl;
import app.factory.ActionFactoryImpl;
import app.factory.Factory;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        TargetableFactory factory = new Factory();
        ActionFactory actionFactory = new ActionFactoryImpl();
        Battlefield battleField = new BattlefieldImplementation(writer, factory, actionFactory);
        Engine engine = new EngineImpl(reader, writer, battleField);

        engine.run();
    }
}
