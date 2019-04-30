package problem_vehicles;

public abstract class AbstractCar implements Drivable, Refuable {
    private double fuelQuantity;
    private double litersPerKm;
    private double traveledDistance;

    public AbstractCar(double fuelQuantity, double litersPerKm) {
        this.setFuelQuantity(fuelQuantity);
        this.setLitresPerKM(litersPerKm);
    }

    @Override
    public String drive(double distance) {
        double currentFuel = this.fuelQuantity;
        double usedFuel = getLitersPerKm() * distance;

        if (usedFuel > currentFuel) {
           return String.format("%s needs refueling", this.getClass().getSimpleName());
        }

        setFuelQuantity(currentFuel - usedFuel);
        this.traveledDistance += (distance);

        return String.format("%s travelled %s km", this.getClass().getSimpleName(), distance);
    }

    protected void setFuelQuantity(double amount) {
        this.fuelQuantity = amount;
    }

    protected void setLitresPerKM(double litersPerKm) {
        this.litersPerKm = litersPerKm;
    }

    private double getLitersPerKm() {
        return this.litersPerKm;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public String getResult(){
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
