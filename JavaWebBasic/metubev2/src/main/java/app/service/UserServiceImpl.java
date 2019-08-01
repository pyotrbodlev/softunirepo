package app.service;

import app.domain.entities.User;
import app.domain.models.UserLoginModel;
import app.domain.models.UserRegisterModel;
import app.repositories.UserRepository;
import app.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(UserRegisterModel userModel) {
        if (!this.userRepository.existUserByUsername(userModel.getUsername())) {
            User user = this.modelMapper.map(userModel, User.class);
            this.userRepository.persist(user);
            return user;
        }

        return null;
    }

    @Override
    public User login(UserLoginModel userLoginModel) {
        User user = this.userRepository.findByUsername(userLoginModel.getUsername()).orElse(null);

        if (user != null && user.getPassword().equals(userLoginModel.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

}
