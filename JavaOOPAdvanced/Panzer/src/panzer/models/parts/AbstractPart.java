package panzer.models.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class AbstractPart implements Part {
    private String model;
    private double weight;
    private BigDecimal price;
    private int additionalParameter;

    protected AbstractPart(String model, double weight, BigDecimal price, int additionalParameter) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.additionalParameter = additionalParameter;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    protected int getAdditionalParameter() {
        return additionalParameter;
    }
}
