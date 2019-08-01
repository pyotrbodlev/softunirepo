package app.repositories;

import app.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existUserByUsername(String username);
}
