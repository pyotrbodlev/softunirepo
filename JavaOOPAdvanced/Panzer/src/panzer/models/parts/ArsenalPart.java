package panzer.models.parts;

import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends AbstractPart implements AttackModifyingPart {
    private int attack;

    public ArsenalPart(String model, double weight, BigDecimal price, int attack) {
        super(model, weight, price);
        this.attack = attack;
    }

    @Override
    public int getAttackModifier() {
        return this.attack;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("+%d %s", this.getAttackModifier(), "Attack");
    }
}
