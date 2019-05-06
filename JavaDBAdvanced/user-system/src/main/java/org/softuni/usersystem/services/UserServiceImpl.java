package org.softuni.usersystem.services;

import org.softuni.usersystem.domain.User;
import org.softuni.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNewUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName("Pesho");
        user.setLastName(username + "ov");

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void makeFriends() {
        User user = this.userRepository.findById(1).get();
        User user1 = this.userRepository.findById(2).get();
        User user2 = this.userRepository.findById(3).get();

        user.setFriends(Set.of(user1, user2));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void getFriendsOf(int id){
        this.userRepository.findById(id).get().getFriendsOf().forEach(System.out::println);
    }
}
