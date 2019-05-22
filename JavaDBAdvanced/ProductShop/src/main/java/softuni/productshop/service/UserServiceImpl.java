package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.*;
import softuni.productshop.domain.entities.User;
import softuni.productshop.parsers.JsonParser;
import softuni.productshop.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
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
    public String saveUser(UserSeedDto userSeedDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<UserSeedDto>> validate = validator.validate(userSeedDto);

        if (validate.size() > 0) {
            return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator()));
        }

        User user = this.modelMapper.map(userSeedDto, User.class);

        this.userRepository.save(user);

        return user.getLastName() + " was registered!";
    }

    @Override
    public List<UserSellerDto> usersWithSoldProducts() {
        List<User> byProductsToSellIsNotNull = this.userRepository.findByProductsToSellIsNotEmpty();

        List<User> collect = byProductsToSellIsNotNull.stream()
                .filter(u -> u.getProductsToSell().stream().anyMatch(p -> p.getBuyer() != null)).collect(Collectors.toList());

        return collect.stream()
                .map(u -> {
                    UserSellerDto userSellerDto = this.modelMapper.map(u, UserSellerDto.class);
                    List<ProductWithBuyerDto> productWithBuyerDtos = u.getProductsToSell().stream().filter(p -> p.getBuyer() != null).map(p -> this.modelMapper.map(p, ProductWithBuyerDto.class)).collect(Collectors.toList());
                    userSellerDto.setSoldProducts(productWithBuyerDtos);
                    return userSellerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsersAndProducts usersAndProducts(){
        List<User> byProductsToSellIsNotNull = this.userRepository.findByProductsToSellIsNotEmpty();

        List<UserWithProductDto> collect = byProductsToSellIsNotNull.stream()
                .filter(u -> u.getProductsToSell().stream().anyMatch(p -> p.getBuyer() != null))
                .map(u -> {
                    UserWithProductDto userWithProductDto = this.modelMapper.map(u, UserWithProductDto.class);
                    List<ProductSeedDto> productWithBuyerDtos = u.getProductsToSell().stream().filter(p -> p.getBuyer() != null).map(p -> this.modelMapper.map(p, ProductSeedDto.class)).collect(Collectors.toList());
                    userWithProductDto.setSoldProducts(productWithBuyerDtos);
                    return userWithProductDto;
                })
                .collect(Collectors.toList());

        UsersAndProducts usersAndProducts = new UsersAndProducts();
        usersAndProducts.setUsers(collect);
        usersAndProducts.setSize(collect.size());

        return usersAndProducts;
    }

    @Override
    public User getUser(Integer id){
        return this.userRepository.findById(id).orElse(null);
    }
}
