package app.repositories;

import app.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("metubev2").createEntityManager();
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(this.entityManager.createQuery("SELECT u FROM User u WHERE u.id LIKE :id", User.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public void persist(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(this.entityManager.createQuery("SELECT u FROM User u WHERE u.username LIKE :username", User.class)
                .setParameter("username", username)
                .getSingleResult());
    }

    @Override
    public Boolean existUserByUsername(String username) {
        return this.findByUsername(username).isPresent();
    }
}
