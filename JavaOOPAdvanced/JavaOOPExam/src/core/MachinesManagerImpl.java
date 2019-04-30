package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;
import entities.FighterImpl;
import entities.TankImpl;

import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {

    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }

    @Override
    public String hirePilot(String name) {
        Pilot pilot = this.pilotFactory.createPilot(name);

        if (this.pilots.containsKey(name)) {
            return String.format(OutputMessages.pilotExists, name);
        }

        this.pilots.put(name, pilot);

        return String.format(OutputMessages.pilotHired, name);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        Tank tank = this.machineFactory.createTank(name, attackPoints, defensePoints);

        if (!this.machines.containsKey(name)) {
            this.machines.put(name, tank);
            return String.format(OutputMessages.tankManufactured, name, attackPoints, defensePoints);
        } else {
            return String.format(OutputMessages.machineExists, name);
        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        Fighter fighter = this.machineFactory.createFighter(name, attackPoints, defensePoints);

        if (!this.machines.containsKey(name)) {
            this.machines.put(name, fighter);
            return String.format(OutputMessages.fighterManufactured, name, attackPoints, defensePoints);
        }

        return String.format(OutputMessages.machineExists, name);
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {

        if (this.machines.containsKey(selectedMachineName) && this.pilots.containsKey(selectedPilotName)) {
            Machine currentMachine = this.machines.get(selectedMachineName);
            Pilot currentPilot = this.pilots.get(selectedPilotName);

            if (currentMachine.getPilot() == null) {
                currentMachine.setPilot(currentPilot);
                currentPilot.addMachine(currentMachine);
            } else {
                return String.format(OutputMessages.machineHasPilotAlready, selectedMachineName);
            }

            return String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
        } else if (!this.machines.containsKey(selectedMachineName)) {
            return String.format(OutputMessages.machineNotFound, selectedMachineName);
        } else {
            return String.format(OutputMessages.pilotNotFound, selectedPilotName);
        }
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        if (!this.machines.containsKey(attackingMachineName)) {
            return String.format(OutputMessages.machineNotFound, attackingMachineName);
        } else if (!this.machines.containsKey(defendingMachineName)) {
            return String.format(OutputMessages.machineNotFound, defendingMachineName);
        } else {
            Machine attacker = this.machines.get(attackingMachineName);
            Machine target = this.machines.get(defendingMachineName);

            if (attacker.getAttackPoints() > target.getDefensePoints()) {
                target.setHealthPoints(target.getHealthPoints() - attacker.getAttackPoints());
            }

            if (target.getHealthPoints() < 0) {
                target.setHealthPoints(0);
            }

            attacker.attack(defendingMachineName);

            return String.format(OutputMessages.attackSuccessful, defendingMachineName, attackingMachineName, target.getHealthPoints());
        }
    }

    @Override
    public String pilotReport(String pilotName) {
        if (this.pilots.containsKey(pilotName)) {
            return this.pilots.get(pilotName).report();
        }

        return String.format(OutputMessages.pilotNotFound, pilotName);
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (this.machines.containsKey(fighterName)) {
            Machine machine = this.machines.get(fighterName);

            if (machine instanceof FighterImpl) {
                Fighter fighter = (Fighter) machine;
                fighter.toggleAggressiveMode();

                return String.format(OutputMessages.fighterOperationSuccessful, fighterName);
            } else {
                return String.format(OutputMessages.notSupportedOperation, fighterName);
            }
        }

        return String.format(OutputMessages.machineNotFound, fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (this.machines.containsKey(tankName)) {
            Machine machine = this.machines.get(tankName);

            if (machine instanceof TankImpl) {
                Tank tank = (Tank) machine;
                tank.toggleDefenseMode();

                return String.format(OutputMessages.tankOperationSuccessful, tankName);
            } else {
                return String.format(OutputMessages.notSupportedOperation, tankName);
            }
        }

        return String.format(OutputMessages.machineNotFound, tankName);
    }
}
