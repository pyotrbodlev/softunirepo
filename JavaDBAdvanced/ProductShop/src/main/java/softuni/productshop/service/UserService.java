package softuni.productshop.service;

import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.dtos.UserSellerDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    String saveUser(UserSeedDto userSeedDto);

    List<UserSellerDto> usersWithSoldProducts();
}
