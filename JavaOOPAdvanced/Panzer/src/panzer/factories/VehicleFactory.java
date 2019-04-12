package panzer.factories;

import panzer.contracts.Vehicle;
import panzer.models.vehicles.Revenger;
import panzer.models.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.List;

public class VehicleFactory {

    public static Vehicle createVehicle(List<String> args){
        String vehicleType = args.get(1);
        String model = args.get(2);
        double weight = Double.parseDouble(args.get(3));
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(args.get(4)));
        int attack = Integer.parseInt(args.get(5));
        int defense = Integer.parseInt(args.get(6));
        int hitPoints = Integer.parseInt(args.get(7));

        switch (vehicleType){
            case "Vanguard":
                return new Vanguard(model, weight, price, attack, defense, hitPoints);

            case "Revenger":
                return new Revenger(model, weight, price, attack, defense, hitPoints);

        }

        return null;
    }
}
