package gringotts.app;

import gringotts.app.core.Engine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PersistenceUnit");

        EntityManager em = emf.createEntityManager();

        Engine engine = new Engine(em);

        engine.run();
    }
}
