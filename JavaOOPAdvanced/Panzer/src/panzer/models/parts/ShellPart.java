package panzer.models.parts;

import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends AbstractPart implements DefenseModifyingPart {
    private int defence;

    public ShellPart(String model, double weight, BigDecimal price, int defense) {
        super(model, weight, price);
        this.defence = defense;
    }

    @Override
    public int getDefenseModifier() {
        return this.defence;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("+%d %s", this.getDefenseModifier(), "Defense");
    }
}
