package org.softuni.usersystem.services;

import org.softuni.usersystem.domain.User;
import org.softuni.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNewUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(username + "@gmail.com");
        user.setFirstName("Pesho");
        user.setLastName(username + "ov");

        this.userRepository.saveAndFlush(user);
    }
}
