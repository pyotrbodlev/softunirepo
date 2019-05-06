package org.softuni.usersystem.controllers;

import org.softuni.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsersController implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
       this.userService.getUsersWithEmailProvider("gmail.com").forEach(e -> System.out.println(e.getFullName()));
    }
}
