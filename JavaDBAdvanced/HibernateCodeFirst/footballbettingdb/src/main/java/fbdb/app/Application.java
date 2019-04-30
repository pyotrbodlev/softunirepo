package fbdb.app;

import fbdb.app.core.Engine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory football_betting_db = Persistence.createEntityManagerFactory("football_betting_db");

        EntityManager entityManager = football_betting_db.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
