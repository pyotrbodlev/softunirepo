package org.softuni.usersystem.repositories;

import org.softuni.usersystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmailEndingWith(String pattern);
}
