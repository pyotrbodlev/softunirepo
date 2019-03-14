package cresla.interfaces;

public interface ModuleFactory {

    Module create(String type, int additionalParameter);
}
