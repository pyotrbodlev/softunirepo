package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

public class CryogenRod extends AbstractModule implements EnergyModule {

    private int energyModule;

    public CryogenRod(int energyModule) {
        super();
        this.energyModule = energyModule;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyModule;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + String.format("Energy Output: %d", this.getEnergyOutput());
    }
}
