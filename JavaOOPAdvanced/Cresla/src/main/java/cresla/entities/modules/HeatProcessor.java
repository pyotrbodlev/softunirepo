package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public class HeatProcessor extends AbstractModule implements AbsorbingModule {

    private int heatAbsorbing;

    public HeatProcessor(int heatAbsorbing) {
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
