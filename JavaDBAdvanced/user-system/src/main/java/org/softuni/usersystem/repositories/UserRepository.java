package org.softuni.usersystem.repositories;

import org.softuni.usersystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
