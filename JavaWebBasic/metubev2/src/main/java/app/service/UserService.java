package app.service;

import app.domain.entities.User;
import app.domain.models.UserLoginModel;
import app.domain.models.UserRegisterModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserRegisterModel userModel);

    User login(UserLoginModel userLoginModel);

    List<User> findAll();

}
