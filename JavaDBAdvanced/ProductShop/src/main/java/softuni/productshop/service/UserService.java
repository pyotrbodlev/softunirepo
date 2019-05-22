package softuni.productshop.service;

import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.dtos.UserSellerDto;
import softuni.productshop.domain.dtos.UsersAndProducts;
import softuni.productshop.domain.entities.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    String saveUser(UserSeedDto userSeedDto);

    List<UserSellerDto> usersWithSoldProducts();

    UsersAndProducts usersAndProducts();

    User getUser(Integer id);
}
