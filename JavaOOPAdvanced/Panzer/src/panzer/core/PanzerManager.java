package panzer.core;

import panzer.constants.Text;
import panzer.contracts.BattleOperator;
import panzer.contracts.Manager;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.factories.PartFactory;
import panzer.factories.VehicleFactory;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PanzerManager implements Manager {
    private static final String VEHICLES_PATH = "panzer.models.vehicles.";

    private Map<String, Vehicle> vehicles;
    private Map<String, Part> parts;
    private BattleOperator battleOperator;

    public PanzerManager() {
        this.vehicles = new LinkedHashMap<>();
        this.parts = new LinkedHashMap<>();
        this.battleOperator = new PanzerBattleOperator();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        Vehicle vehicle = VehicleFactory.createVehicle(arguments);
        this.vehicles.put(arguments.get(2), vehicle);

        return String.format(Text.VEHICLE_CREATED, arguments.get(1), arguments.get(2));
    }

    @Override
    public String addPart(List<String> arguments) {

        String partType = arguments.get(2);

        Part part = PartFactory.createPart(arguments);

        if (this.vehicles.containsKey(arguments.get(1))){
            Vehicle currentVehicle = this.vehicles.get(arguments.get(1));
            switch (partType){
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

        return String.format(Text.PART_CREATED, partType, arguments.get(3), arguments.get(1));
    }

    @Override
    public String inspect(List<String> arguments) {
        return null;
    }

    @Override
    public String battle(List<String> arguments) {
        return null;
    }

    @Override
    public String terminate(List<String> arguments) {
        return null;
    }
}
