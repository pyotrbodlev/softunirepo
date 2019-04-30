package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private final double attackPointsModifier = 40.0;
    private final double deffencePointsModifier = 30.0;

    private boolean defenseMode;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 100);
        this.defenseMode = true;
    }

    @Override
    void setAttackPoints(double attackPoints) {
        super.setAttackPoints(attackPoints - attackPointsModifier);
    }

    @Override
    void setDefensePoints(double defensePoints) {
        super.setDefensePoints(defensePoints + deffencePointsModifier);
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }

    @Override
    public void toggleDefenseMode() {
        this.defenseMode = !this.defenseMode;

        if (this.defenseMode){
            this.setAttackPoints(super.getAttackPoints() - attackPointsModifier);
            this.setDefensePoints(super.getDefensePoints() + deffencePointsModifier);
        } else {
            this.setAttackPoints(super.getAttackPoints() + attackPointsModifier);
            this.setDefensePoints(super.getDefensePoints() - deffencePointsModifier);
        }

    }

    @Override
    public String toString() {
        return " *Type: Tank" + System.lineSeparator() + super.toString() + System.lineSeparator() + String.format(" *Defense Mode(%s)", this.getDefenseMode() ? "ON" : "OFF");
    }

}
