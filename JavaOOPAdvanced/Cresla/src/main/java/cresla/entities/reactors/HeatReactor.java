package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;

public class HeatReactor extends AbstractReactor {
    private int heatReductionIndex;
    private int capacity;

    public HeatReactor(int parametr, int capacity) {
        super(new ModuleContainer(capacity));
        this.heatReductionIndex = parametr;
        this.capacity = capacity;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReductionIndex;
    }
}
