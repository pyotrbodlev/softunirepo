package app.contracts;


public interface TargetableFactory {
    Targetable create(String name, String className);
}
