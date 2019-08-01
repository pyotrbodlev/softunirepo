package app.repositories;

import app.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("metubev2").createEntityManager();
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager.createQuery("SELECT t FROM Tube t", Tube.class).getResultList();
    }

    @Override
    public Optional<Tube> findById(String id) {
        return Optional.ofNullable(this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.id LIKE :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public void persist(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
    }
}
