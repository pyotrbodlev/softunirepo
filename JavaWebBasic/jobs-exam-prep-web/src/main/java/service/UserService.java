package service;

import domain.models.service.UserServiceModel;

public interface UserService {
    void saveUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
