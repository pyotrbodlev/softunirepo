package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private final double attackPointsModifier = 50.0;
    private final double deffencePointsModifier = 25.0;

    private boolean aggressiveMode;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 200);
        this.aggressiveMode = true;
    }

    @Override
    void setAttackPoints(double attackPoints) {
        super.setAttackPoints(attackPoints + attackPointsModifier);
    }

    @Override
    void setDefensePoints(double defensePoints) {
        super.setDefensePoints(defensePoints - deffencePointsModifier);
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        this.aggressiveMode = !this.aggressiveMode;

        if (this.aggressiveMode) {
            this.setAttackPoints(super.getAttackPoints() + attackPointsModifier);
            this.setDefensePoints(super.getDefensePoints() - deffencePointsModifier);
        } else {
            this.setAttackPoints(super.getAttackPoints() - attackPointsModifier);
            this.setDefensePoints(super.getDefensePoints() + deffencePointsModifier);
        }
    }

    @Override
    public String toString() {
        return " *Type: Fighter" + System.lineSeparator() + super.toString() + System.lineSeparator() + String.format(" *Aggressive Mode(%s)", this.getAggressiveMode() ? "ON" : "OFF");
    }
}
