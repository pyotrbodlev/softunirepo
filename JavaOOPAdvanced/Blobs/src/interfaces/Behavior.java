package interfaces;

import models.Blob;

public interface Behavior {
    void trigger(Blob source);
    void applyRecurrentEffect(Blob source);

    boolean isTriggered();

    void setIsTriggered(boolean isTriggered);

    boolean toDelayRecurrentEffect();

    void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect);
}
