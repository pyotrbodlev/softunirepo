package repository;

import domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {
    private EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JobApplication> findAll() {
        return this.entityManager.createQuery("SELECT j FROM JobApplication j", JobApplication.class).getResultList();
    }

    @Override
    public JobApplication findById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT j FROM JobApplication j WHERE j.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void persist(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void remove(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public long count() {
        return this.entityManager.createQuery("SELECT COUNT(j) FROM JobApplication j", Long.class).getSingleResult();
    }
}
