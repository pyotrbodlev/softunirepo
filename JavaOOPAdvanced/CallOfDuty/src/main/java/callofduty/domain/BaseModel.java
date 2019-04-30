package callofduty.domain;

import callofduty.interfaces.Identifiable;

public abstract class BaseModel implements Identifiable {
    private String id;

    protected BaseModel(String id) {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
