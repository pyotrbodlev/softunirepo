package app.repository;

import app.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("mytube").createEntityManager();
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager.createQuery("SELECT t FROM Tube t", Tube.class).getResultList();

    }

    @Override
    public Tube findById(String id) {
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.id LIKE :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public void persist(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Tube findByTitle(String title) {
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.title LIKE :title", Tube.class)
                .setParameter("title", title)
                .getSingleResult();
    }
}
