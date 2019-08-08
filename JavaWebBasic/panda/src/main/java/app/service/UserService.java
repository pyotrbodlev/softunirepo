package app.service;

import app.domain.models.sevice.UserServiceModel;

public interface UserService {
    UserServiceModel saveUser(UserServiceModel userServiceModel);

    UserServiceModel getById(String id);

    UserServiceModel getByUsername(String username);
}
