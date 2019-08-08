package app.repository;

import java.util.List;

public interface GenericRepository<E, I> {
    List<E> findAll();

    E findById(I id);

    void persist(E entity);

    void remove(E entity);

    long count();
}
