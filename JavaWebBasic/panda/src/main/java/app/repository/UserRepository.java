package app.repository;

import app.domain.entites.User;

public interface UserRepository extends GenericRepository<User, String> {
    User getUserByUsername(String username);
}
