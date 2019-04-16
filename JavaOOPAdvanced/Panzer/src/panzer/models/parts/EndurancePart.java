package panzer.models.parts;

import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends AbstractPart implements HitPointsModifyingPart {
    private int hitpoints;

    public EndurancePart(String model, double weight, BigDecimal price, int hitpoints) {
        super(model, weight, price);
        this.hitpoints = hitpoints;
    }

    @Override
    public int getHitPointsModifier() {
        return this.hitpoints;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("+%d %s", this.getHitPointsModifier(), "HitPoints");
    }
}
