package core;

import core.interfaces.MachinesManager;
import io.ConsoleWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private MachinesManager machinesManager;
    private ConsoleWriter writer;
    private BufferedReader reader;

    public Engine(MachinesManager machinesManager) {
        this.machinesManager = machinesManager;
        this.writer = new ConsoleWriter();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        while (true) {
            String line = reader.readLine();

            if (line.equals("Over")) {
                break;
            }

            String[] tokens = line.split(" ");

            String command = tokens[0];

            switch (command) {
                case "Hire":
                    String pilotName = tokens[1];
                    this.writer.writeLine(this.machinesManager.hirePilot(pilotName));
                    break;
                case "Report":
                    pilotName = tokens[1];
                    this.writer.writeLine(this.machinesManager.pilotReport(pilotName));
                    break;
                case "ManufactureTank":
                    String tankName = tokens[1];
                    double attackPoints = Double.parseDouble(tokens[2]);
                    double defensePoints = Double.parseDouble(tokens[3]);
                    this.writer.writeLine(this.machinesManager.manufactureTank(tankName, attackPoints, defensePoints));
                    break;
                case "ManufactureFighter":
                    String fighterName = tokens[1];
                    attackPoints = Double.parseDouble(tokens[2]);
                    defensePoints = Double.parseDouble(tokens[3]);
                    this.writer.writeLine(this.machinesManager.manufactureFighter(fighterName, attackPoints, defensePoints));
                    break;
                case "Engage":
                    pilotName = tokens[1];
                    String machineName = tokens[2];

                    this.writer.writeLine(this.machinesManager.engageMachine(pilotName, machineName));
                    break;
                case "Attack":
                    String attackingMachineName = tokens[1];
                    String defendingMachineName = tokens[2];
                    this.writer.writeLine(this.machinesManager.attackMachines(attackingMachineName, defendingMachineName));
                    break;
                case "DefenseMode":
                    machineName = tokens[1];
                    this.writer.writeLine(this.machinesManager.toggleTankDefenseMode(machineName));
                    break;
                case "AggressiveMode":
                    machineName = tokens[1];
                    this.writer.writeLine(this.machinesManager.toggleFighterAggressiveMode(machineName));
                    break;

            }
        }
    }
}
