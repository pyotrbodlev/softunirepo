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
        return super.getTotalEnergyOutput() * this.cryoProductionIndex;
    }

}
