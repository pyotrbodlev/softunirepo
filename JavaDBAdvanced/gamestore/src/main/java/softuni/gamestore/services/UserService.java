package softuni.gamestore.services;

import softuni.gamestore.domain.dtos.UserLoginDto;
import softuni.gamestore.domain.dtos.UserRegistrationDto;
import softuni.gamestore.domain.entities.User;

public interface UserService {
    String registerUser(UserRegistrationDto userRegistrationDto);

    User loginUser(UserLoginDto userLoginDto);
}
