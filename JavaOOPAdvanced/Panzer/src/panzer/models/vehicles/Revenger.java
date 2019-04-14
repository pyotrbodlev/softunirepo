package panzer.models.vehicles;

import panzer.contracts.Part;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Revenger extends AbstractVehicle {
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price.multiply(new BigDecimal(1.50)), (int) (attack * 2.50), (int) (defense * 0.50), (int) (hitPoints * 0.50));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Revenger - ").append(super.getModel()).append("\n");
        str.append(String.format("Total Weight: %.3f", super.getTotalWeight())).append("\n");
        str.append(String.format("Total Price: %.3f", super.getTotalPrice())).append("\n");
        str.append("Attack: ").append(super.getTotalAttack()).append("\n");
        str.append("Defense: ").append(super.getTotalDefense()).append("\n");
        str.append("HitPoints: ").append(super.getTotalHitPoints()).append("\n");
        List<Part> toJoin = new ArrayList<Part>((Collection<? extends Part>) super.getParts());
        if (toJoin.size() == 0) {
            str.append("Parts: None");
        } else {
            List<String> toJoin2 = new ArrayList<>();
            for (int i = 0; i < toJoin.size(); i++) {
                toJoin2.add(toJoin.get(i).getModel());
            }
            str.append("Parts: ").append(String.join(", ", toJoin2));
        }
        return str.toString();
    }
}
