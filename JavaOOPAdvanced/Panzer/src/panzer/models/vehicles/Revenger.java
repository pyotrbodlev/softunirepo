package panzer.models.vehicles;

import java.math.BigDecimal;

public class Revenger extends AbstractVehicle {
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
    }

    @Override
    protected void setPrice(BigDecimal price) {
        super.setPrice(price.multiply(BigDecimal.valueOf(150)).divide(BigDecimal.valueOf(100)));
    }

    @Override
    protected void setAttack(int attack) {
        super.setAttack(attack * 250 / 100);
    }

    @Override
    protected void setDefense(int defense) {
        super.setDefense(defense * 50 / 100);
    }

    @Override
    protected void setHitPoints(int hitPoints) {
        super.setHitPoints(hitPoints * 50 / 100);
    }
}
