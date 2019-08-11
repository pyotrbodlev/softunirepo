package service;

import domain.entities.User;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.UserRepository;
import utils.ValidationUtil;

import javax.inject.Inject;

public class UserServiceIImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Inject
    public UserServiceIImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        if (this.validationUtil.isValid(user)) {
            this.userRepository.persist(user);
        }
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User userByUsername = this.userRepository.getUserByUsername(username);
        if (userByUsername != null) {
            return this.modelMapper.map(userByUsername, UserServiceModel.class);
        } else {
            return null;
        }
    }
}
