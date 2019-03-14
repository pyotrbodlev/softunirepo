package cresla.entities.reactors;

import cresla.constants.Text;
import cresla.entities.IDCreator;
import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractReactor implements Reactor {

    private int id;
    private Container container;

    protected AbstractReactor(Container container) {
        this.id = ++IDCreator.id;
        this.container = container;
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.container.getTotalEnergyOutput() > this.getTotalHeatAbsorbing() ? 0 : this.container.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.container.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        try {
            Field modulesByInput = ModuleContainer.class.getDeclaredField("modulesByInput");
            modulesByInput.setAccessible(true);
            List<?> list = (List<?>) modulesByInput.get(this.container);
            return list.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.container.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.container.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(" - ").append(this.getId()).append(System.lineSeparator());
        sb.append(String.format("Energy Output: %d", this.getTotalEnergyOutput())).append(System.lineSeparator());
        sb.append(String.format("Heat Absorbing: %d", this.getTotalHeatAbsorbing())).append(System.lineSeparator());
        sb.append(String.format("Modules: %d", this.getModuleCount()));
        return sb.toString();
    }

//    private boolean isOverheated() {
//        return this.getTotalEnergyOutput() <= this.getTotalHeatAbsorbing();
//    }
}
