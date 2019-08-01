package app.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, K> {
    List<E> findAll();

    Optional<E> findById(K id);

    void persist(E entity);
}
