package panzer.models.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class AbstractPart implements Part {
    private String model;
    private double weight;
    private BigDecimal price;

    protected AbstractPart(String model, double weight, BigDecimal price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("%s Part - %s", this.getClass().getSimpleName(), this.getModel());
    }
}
