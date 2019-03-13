package models.behavors;

import models.Blob;

public class Aggressive extends AbstractBehavior {

    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private int sourceInitialDamage;

    public Aggressive() {
        super();
    }

    public void trigger(Blob source) {
        this.sourceInitialDamage = source.getDamage();
        super.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }

    public void applyRecurrentEffect(Blob source) {
        if (super.toDelayRecurrentEffect()) {
            super.setToDelayRecurrentEffect(false);
        } else {
            source.setDamage(source.getDamage() - AGGRESSIVE_DAMAGE_DECREMENT);

            if (source.getDamage() <= this.sourceInitialDamage) {
                source.setDamage(this.sourceInitialDamage);
            }
        }
    }
//    private void applyAggressiveRecurrentEffect() {
//        if (((Aggressive)this.behavior).toDelayRecurrentEffect()) {
//            ((Aggressive)this.behavior).setToDelayRecurrentEffect(false);
//        } else {
//            this.setDamage(this.getDamage() - 5);
//
//            if (this.getDamage() <= this.initialHealth) {
//                this.setDamage(this.initialDamage);
//            }
//        }
//    }

    public boolean toDelayRecurrentEffect() {
        return super.toDelayRecurrentEffect();
    }

//    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect){
//        super.toDelayRecurrentEffect = toDelayRecurrentEffect;
//    }

    private void applyTriggerEffect(Blob source) {
        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }
}
