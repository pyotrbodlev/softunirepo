package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.CustomerRegisterDto;
import softuni.cardealer.domain.dtos.ListWithCustomerSellDto;
import softuni.cardealer.domain.dtos.ListWithCustomersDto;
import softuni.cardealer.domain.entites.Customer;

public interface CustomerService {
    String saveCustomer(CustomerRegisterDto dto);

    Customer getCustomer(Integer id);

    int getCustomerCount();

    ListWithCustomersDto getListWithCustomersOrderedByBirthDate();

    ListWithCustomerSellDto getListWithCustomerSellDto();
}
