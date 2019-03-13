package models.behavors;

import interfaces.Behavior;
import models.Blob;

public abstract class AbstractBehavior implements Behavior {

    private boolean isTriggered;
    private boolean toDelayRecurrentEffect;

    public AbstractBehavior() {
        this.toDelayRecurrentEffect = true;
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    @Override
    public void setIsTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    @Override
    public boolean toDelayRecurrentEffect() {
        return this.toDelayRecurrentEffect;
    }

    @Override
    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect) {
        this.toDelayRecurrentEffect = toDelayRecurrentEffect;
    }

}
