package fbdb.app.core;

import javax.persistence.EntityManager;

public class Engine {
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run(){

    }
}
