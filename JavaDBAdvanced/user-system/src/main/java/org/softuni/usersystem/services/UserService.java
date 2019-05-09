package org.softuni.usersystem.services;

import org.softuni.usersystem.domain.User;

import java.util.List;

public interface UserService {
    void addNewUser(String username, String password, String email);

    void makeFriends();

    void getFriendsOf(int id);

    List<User> getUsersWithEmailProvider(String provider);
}
