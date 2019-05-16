package softuni.gamestore.services;

import softuni.gamestore.domain.dtos.UserActiveDto;

public interface OrderService {
    String addOrder(UserActiveDto userActiveDto, String title);

    String removeGameFromOrder(UserActiveDto userActiveDto, String title);

    String buyItems(UserActiveDto userActiveDto);
}
