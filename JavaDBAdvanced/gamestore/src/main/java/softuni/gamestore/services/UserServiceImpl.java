package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.domain.dtos.UserActiveDto;
import softuni.gamestore.domain.dtos.UserLoginDto;
import softuni.gamestore.domain.dtos.UserRegistrationDto;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.Role;
import softuni.gamestore.domain.entities.User;
import softuni.gamestore.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(UserRegistrationDto userRegistrationDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return "Password doesn't match";
        }

        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);

        if (constraintViolations.size() > 0) {
            StringBuilder sb = new StringBuilder();
            constraintViolations.forEach(c -> sb.append(c.getMessage()).append(System.lineSeparator()));
            return sb.toString().trim();
        }

        if (this.userRepository.findByEmailAndPassword(userRegistrationDto.getEmail(), userRegistrationDto.getPassword()) != null) {
            return "This user is already registered";
        }

        User user = this.modelMapper.map(userRegistrationDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.NORMAL);
        }

        this.userRepository.save(user);

        return String.format("%s was registered", userRegistrationDto.getFullName());
    }

    @Override
    public UserActiveDto loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user != null) {
            UserActiveDto userActiveDto = this.modelMapper.map(user, UserActiveDto.class);
            userActiveDto.setOwnedGames(user.getGames().stream().map(Game::getTitle).collect(Collectors.toList()));
            return userActiveDto;
        } else {
            return null;
        }
    }

}
