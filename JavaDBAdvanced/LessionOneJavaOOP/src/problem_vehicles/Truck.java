package problem_vehicles;

public class Truck extends AbstractCar {
    private static final double FUEL_CONSUMMATION_IN_SUMMER = 1.6;
    private static final double FUEL_MULTIPLIER_FOR_TRUCK = 0.95;

    public Truck(double fuelQuantity, double litersPerKm) {
        super(fuelQuantity, litersPerKm);
    }

    @Override
    protected void setLitresPerKM(double litersPerKm) {
        super.setLitresPerKM(litersPerKm + FUEL_CONSUMMATION_IN_SUMMER);
    }

    @Override
    public void refuel(double amount) {
        super.setFuelQuantity(super.getFuelQuantity() + (amount * FUEL_MULTIPLIER_FOR_TRUCK));
    }
}
