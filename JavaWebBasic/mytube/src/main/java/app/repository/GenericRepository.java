package app.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    List<E> findAll();

    E findById(K id);

    void persist(E tube);

    E findByTitle(String title);
}
