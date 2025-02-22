package factories;

import core.interfaces.MachineFactory;
import entities.interfaces.Fighter;
import entities.interfaces.Tank;
import entities.FighterImpl;
import entities.TankImpl;

public class MachineFactoryImpl implements MachineFactory {
    @Override
    public Tank createTank(String name, double attackPoints, double defensePoints) {
        return new TankImpl(name, attackPoints, defensePoints);
    }

    @Override
    public Fighter createFighter(String name, double attackPoints, double defensePoints) {
        return new FighterImpl(name, attackPoints, defensePoints);
    }
}
