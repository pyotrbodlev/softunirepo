package panzer.models.parts;

import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends AbstractPart implements DefenseModifyingPart {
    public ShellPart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price, additionalParameter);
    }

    @Override
    public int getDefenseModifier() {
        return this.getAdditionalParameter();
    }
}
