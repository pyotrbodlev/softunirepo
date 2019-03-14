package cresla.controller;

import cresla.constants.Text;
import cresla.factories.ModuleFactory;
import cresla.factories.ReactorFactory;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProgramManager implements Manager {
    private Map<Integer, Reactor> reactorMap;
    private Map<Integer, Module> moduleMap;

    public ProgramManager() {
        this.reactorMap = new LinkedHashMap<>();
        this.moduleMap = new LinkedHashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        String type = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int moduleCapacity = Integer.parseInt(arguments.get(3));

        Reactor reactor = ReactorFactory.create(type, additionalParameter, moduleCapacity);

        if (reactor != null) {
            this.reactorMap.put(reactor.getId(), reactor);
            return String.format(Text.REACTOR_CREATED_SUCCESS, type, reactor.getId());
        } else {
            return Text.REACTOR_CREATED_ERROR;
        }
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        //Module {reactorId} {type} {additionalParameter}
        int reactorId = Integer.parseInt(arguments.get(1));
        String type = arguments.get(2);
        int additionalParameter = Integer.parseInt(arguments.get(3));

        Module module = ModuleFactory.create(type, additionalParameter);

        if ("CryogenRod".equals(type)) {
            this.reactorMap.get(reactorId).addEnergyModule((EnergyModule) module);
        } else {
            this.reactorMap.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        }

        this.moduleMap.put(module.getId(), module);

        return String.format(Text.MODULE_CREATED_SUCCESSFUL, type, module.getId(), reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int id = Integer.parseInt(arguments.get(1));

        if (reactorMap.containsKey(id)){
            return reactorMap.get(id).toString();
        } else {
            return moduleMap.get(id).toString();
        }

    }

    @Override
    public String exitCommand(List<String> arguments) {
        return this.toString();
    }

    @Override
    public String toString() {
        int cryoReactorsCount = 0;
        int heatReactorsCount = 0;
        int energyModulesCount = 0;
        int absorbingModulesCount = 0;
        long totalEnergyOutput;
        long totalHeatAbsorbing;

        for (Reactor reactor : reactorMap.values()) {
            if (reactor.getClass().getSimpleName().equals("CryoReactor")){
                cryoReactorsCount++;
            } else {
                heatReactorsCount++;
            }
        }

        for (Module module : moduleMap.values()) {
            if (module.getClass().getSimpleName().equals("CryogenRod")){
                energyModulesCount++;
            } else {
                absorbingModulesCount++;
            }
        }

        totalEnergyOutput = this.reactorMap.values().stream().mapToLong(Reactor::getTotalEnergyOutput).sum();
        totalHeatAbsorbing = this.reactorMap.values().stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();


        return "Cryo Reactors: " + cryoReactorsCount + System.lineSeparator() +
                "Heat Reactors: " + heatReactorsCount + System.lineSeparator() +
                "Energy Modules: " + energyModulesCount + System.lineSeparator() +
                "Absorbing Modules: " + absorbingModulesCount + System.lineSeparator() +
                "Total Energy Output: " + totalEnergyOutput + System.lineSeparator() +
                "Total Heat Absorbing: " + totalHeatAbsorbing;
    }
}
