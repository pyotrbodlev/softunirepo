package panzer.models.parts;

import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends AbstractPart implements AttackModifyingPart {
    public ArsenalPart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price, additionalParameter);
    }

    @Override
    public int getAttackModifier() {
        return this.getAdditionalParameter();
    }
}
