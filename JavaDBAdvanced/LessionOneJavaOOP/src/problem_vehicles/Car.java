package problem_vehicles;

public class Car extends AbstractCar {
    private static final double FUEL_CONSUMMATION_IN_SUMMER = 0.9;

    public Car(double fuelQuantity, double litersPerKm) {
        super(fuelQuantity, litersPerKm);
    }

    @Override
    protected void setLitresPerKM(double litersPerKm) {
        super.setLitresPerKM(litersPerKm + FUEL_CONSUMMATION_IN_SUMMER);
    }

    @Override
    public void refuel(double amount) {
        super.setFuelQuantity(super.getFuelQuantity() + amount);
    }
}
