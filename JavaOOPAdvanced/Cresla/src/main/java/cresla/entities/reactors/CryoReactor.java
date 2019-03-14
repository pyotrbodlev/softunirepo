package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;

public class CryoReactor extends AbstractReactor {

    private int cryoProductionIndex;
    private int capacity;

    public CryoReactor(int parametr, int capacity) {
        super(new ModuleContainer(capacity));
        this.cryoProductionIndex = parametr;
        this.capacity = capacity;
    }

    @Override
    public long getTotalEnergyOutput() {
        long result = super.getTotalEnergyOutput() * this.cryoProductionIndex;

        return result > this.getTotalHeatAbsorbing() ? 0 : result;

    }

}
