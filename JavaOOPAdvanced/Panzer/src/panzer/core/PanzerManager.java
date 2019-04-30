package panzer.core;

import panzer.contracts.BattleOperator;
import panzer.contracts.Manager;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.factories.PartFactory;
import panzer.factories.VehicleFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PanzerManager implements Manager {
    private Map<String, Vehicle> vehicles;
    private List<String> defeatedVehicles;
    private Map<String, Part> parts;
    private BattleOperator battleOperator;
    private List<String> remainingVehicles;

    public PanzerManager() {
        this.vehicles = new LinkedHashMap<>();
        this.parts = new LinkedHashMap<>();
        this.defeatedVehicles = new ArrayList<>();
        this.battleOperator = new PanzerBattleOperator();
        this.remainingVehicles = new ArrayList<>();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        Vehicle vehicle = VehicleFactory.createVehicle(arguments);
        this.vehicles.put(arguments.get(2), vehicle);
        this.remainingVehicles.add(arguments.get(2));

        return "Created " + arguments.get(1) + " Vehicle - " + arguments.get(2);
    }

    @Override
    public String addPart(List<String> arguments) {

        String partType = arguments.get(2);

        Part part = PartFactory.createPart(arguments);

        if (this.vehicles.containsKey(arguments.get(1))) {
            Vehicle currentVehicle = this.vehicles.get(arguments.get(1));
            switch (partType) {
                case "Shell":
                    currentVehicle.addShellPart(part);
                    break;
                case "Arsenal":
                    currentVehicle.addArsenalPart(part);
                    break;
                case "Endurance":
                    currentVehicle.addEndurancePart(part);
                    break;
            }
        }

        this.parts.put(arguments.get(3), part);

        return "Added " + arguments.get(2) + " - " + arguments.get(3) + " to Vehicle - " + arguments.get(1);
    }

    @Override
    public String inspect(List<String> arguments) {
        String modelName = arguments.get(1);

        if (this.vehicles.containsKey(modelName)) {
            return this.vehicles.get(modelName).toString();
        } else if (this.parts.containsKey(modelName)) {
            return this.parts.get(modelName).toString();
        }

        return null;
    }

    @Override
    public String battle(List<String> arguments) {
        String attacker = arguments.get(1);
        String target = arguments.get(2);

        Vehicle attackerVehicle = this.vehicles.get(attacker);
        Vehicle targetVehicle = this.vehicles.get(target);

        String winner = this.battleOperator.battle(attackerVehicle, targetVehicle);

        if (winner.equals(attacker)) {
            this.defeatedVehicles.add(target);
            this.remainingVehicles.add(attacker);
        } else {
            this.defeatedVehicles.add(attacker);
            this.remainingVehicles.add(target);
        }

        return arguments.get(1)
                + " versus " + arguments.get(2) +
                " -> " + winner + " Wins! Flawless Victory!";
    }

    @Override
    public String terminate(List<String> arguments) throws NoSuchFieldException, IllegalAccessException {
        List<String> currentlyUsedParts = new ArrayList<>();

        for (Vehicle vehicle : vehicles.values()) {
            for (Part part : vehicle.getParts()) {
                currentlyUsedParts.add(part.getModel());
            }
        }

        return String.format("Remaining Vehicles: %s", remainingVehicles.isEmpty() ? "None" : remainingVehicles.toString().replaceAll("[\\[\\]]", "")) + System.lineSeparator() +
                String.format("Defeated Vehicles: %s", defeatedVehicles.isEmpty() ? "None" : defeatedVehicles.toString().replaceAll("[\\[\\]]", "")) + System.lineSeparator() +
                String.format("Currently Used Parts: %d", currentlyUsedParts.size()) + System.lineSeparator();
    }
}
