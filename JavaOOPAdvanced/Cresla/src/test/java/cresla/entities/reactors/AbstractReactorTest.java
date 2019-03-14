package cresla.entities.reactors;

import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractReactorTest {
    private Reactor reactor;

    @Before
    public void setUp() throws Exception {
        this.reactor = new CryoReactor(10, 10);
        EnergyModule energyModule = new CryogenRod(100);
        EnergyModule energyModule1 = new CryogenRod(100);
        this.reactor.addEnergyModule(energyModule);
        this.reactor.addEnergyModule(energyModule1);
        AbsorbingModule absorbingModule = new HeatProcessor(2001);
        this.reactor.addAbsorbingModule(absorbingModule);
    }

    @Test
    public void getTotalEnergyOutput() {
        System.out.println(this.reactor.getTotalEnergyOutput());
    }
}