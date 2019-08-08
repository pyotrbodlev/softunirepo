package app.service;

import app.domain.entites.Role;
import app.domain.entites.User;
import app.domain.models.sevice.UserServiceModel;
import app.repository.UserRepository;
import app.utils.ValidationUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserServiceModel saveUser(UserServiceModel userServiceModel) {
        if (this.validationUtil.isValid(userServiceModel)
                && userServiceModel.getPassword().equals(userServiceModel.getConfirmPassword())) {

            if (this.userRepository.count() == 0) {
                userServiceModel.setRole(Role.ADMIN);
            } else {
                userServiceModel.setRole(Role.USER);
            }

            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
            this.userRepository.persist(user);

            return userServiceModel;
        } else {
            return null;
        }
    }

    @Override
    public UserServiceModel getById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        return this.modelMapper.map(this.userRepository.getUserByUsername(username), UserServiceModel.class);
    }

}
