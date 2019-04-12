package panzer.models.parts;

import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends AbstractPart implements HitPointsModifyingPart {
    public EndurancePart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price, additionalParameter);
    }

    @Override
    public int getHitPointsModifier() {
        return this.getAdditionalParameter();
    }
}
