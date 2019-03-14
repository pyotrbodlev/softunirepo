package cresla.interfaces;

public interface ReactorFactory {
    Reactor create(String type, int additionalParameter, int moduleCapacity);
}
