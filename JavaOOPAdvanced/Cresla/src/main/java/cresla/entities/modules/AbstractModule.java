package cresla.entities.modules;

import cresla.entities.IDCreator;
import cresla.interfaces.Module;

public abstract class AbstractModule implements Module {

    private int id;

    protected AbstractModule() {
        this.id = ++IDCreator.id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s Module - %d", this.getClass().getSimpleName(), this.getId());
    }
}
