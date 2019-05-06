package org.softuni.usersystem.services;

public interface UserService {
    void addNewUser(String username, String password, String email);

    void makeFriends();

    void getFriendsOf(int id);
}
