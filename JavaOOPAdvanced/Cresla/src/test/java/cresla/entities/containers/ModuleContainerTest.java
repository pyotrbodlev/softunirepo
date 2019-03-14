package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import org.junit.Assert;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Map;

public class ModuleContainerTest {
    private Container container;

    @org.junit.Before
    public void setUp() throws Exception {
        this.container = new ModuleContainer(10);
    }

    @org.junit.Test
    public void addEnergyModule() throws NoSuchFieldException, IllegalAccessException {
        EnergyModule energyModule = Mockito.mock(EnergyModule.class);
        this.container.addEnergyModule(energyModule);

        Field energyModules = ModuleContainer.class.getDeclaredField("energyModules");
        energyModules.setAccessible(true);
        Map<?, ?> list = (Map<?, ?>) energyModules.get(this.container);

        Assert.assertEquals(1, list.size());
    }

    @org.junit.Test
    public void addAbsorbingModule() throws NoSuchFieldException, IllegalAccessException {
        AbsorbingModule energyModule = Mockito.mock(AbsorbingModule.class);
        this.container.addAbsorbingModule(energyModule);

        Field absorbingModules = ModuleContainer.class.getDeclaredField("absorbingModules");
        absorbingModules.setAccessible(true);
        Map<?, ?> list = (Map<?, ?>) absorbingModules.get(this.container);

        Assert.assertEquals(1, list.size());
    }

    @org.junit.Test
    public void getTotalEnergyOutput() {
        EnergyModule energyModule1 = Mockito.mock(EnergyModule.class);
        EnergyModule energyModule2 = Mockito.mock(EnergyModule.class);
        Mockito.when(energyModule1.getId()).thenReturn(1);
        Mockito.when(energyModule2.getId()).thenReturn(2);

        Mockito.when(energyModule1.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(energyModule2.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);

        this.container.addEnergyModule(energyModule1);
        this.container.addEnergyModule(energyModule2);

        long expected = (long)Integer.MAX_VALUE * 2;
        long actual = this.container.getTotalEnergyOutput();

        Assert.assertEquals(expected, actual);

    }

    @org.junit.Test
    public void getTotalHeatAbsorbing() {
        AbsorbingModule absorbingModule1 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule absorbingModule2 = Mockito.mock(AbsorbingModule.class);

        Mockito.when(absorbingModule1.getId()).thenReturn(1);
        Mockito.when(absorbingModule2.getId()).thenReturn(2);

        Mockito.when(absorbingModule1.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(absorbingModule2.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);

        this.container.addAbsorbingModule(absorbingModule1);
        this.container.addAbsorbingModule(absorbingModule2);

        long expected = (long)Integer.MAX_VALUE * 2;
        long actual = this.container.getTotalHeatAbsorbing();

        Assert.assertEquals(expected, actual);
    }
}