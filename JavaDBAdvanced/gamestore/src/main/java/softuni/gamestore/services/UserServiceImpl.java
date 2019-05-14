package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.domain.dtos.UserLoginDto;
import softuni.gamestore.domain.dtos.UserRegistrationDto;
import softuni.gamestore.domain.entities.Role;
import softuni.gamestore.domain.entities.User;
import softuni.gamestore.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
    public User loginUser(UserLoginDto userLoginDto) {
        return this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
    }
}
