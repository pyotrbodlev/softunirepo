package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public class CooldownSystem extends AbstractModule implements AbsorbingModule {
    private int heatAbsorbing;

    public CooldownSystem(int heatAbsorbing) {
        super();
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + String.format("Heat Absorbing: %d", this.getHeatAbsorbing());
    }
}
