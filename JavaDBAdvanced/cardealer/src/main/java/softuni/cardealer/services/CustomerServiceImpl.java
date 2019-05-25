package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.*;
import softuni.cardealer.domain.entites.Car;
import softuni.cardealer.domain.entites.Customer;
import softuni.cardealer.domain.entites.Part;
import softuni.cardealer.domain.entites.Sale;
import softuni.cardealer.repositories.CustomerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveCustomer(CustomerRegisterDto dto){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CustomerRegisterDto>> constraintViolations = validator.validate(dto);

        if (constraintViolations.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<CustomerRegisterDto> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }

            return sb.toString();
        }

        Customer customer = this.modelMapper.map(dto, Customer.class);

        this.customerRepository.save(customer);

        return dto.getName() + Text.SUCCESSFUL_REGISTER;
    }

    @Override
    public Customer getCustomer(Integer id){
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public int getCustomerCount(){
        return (int) this.customerRepository.count();
    }

    @Override
    public ListWithCustomersDto getListWithCustomersOrderedByBirthDate(){
        List<CustomerXmlDto> collect = this.customerRepository.findAllOrderByBirthDate().stream().map(c -> this.modelMapper.map(c, CustomerXmlDto.class)).collect(Collectors.toList());

        return new ListWithCustomersDto(collect);
    }

    @Override
    public ListWithCustomerSellDto getListWithCustomerSellDto(){
        List<Customer> all = this.customerRepository.findAllWithAtLeastOneBoughtCar();

        List<CustomerSellXmlDto> collect = all.stream().map(c -> {
            CustomerSellXmlDto dto = this.modelMapper.map(c, CustomerSellXmlDto.class);

            dto.setBoughtCars(c.getSales().size());

            BigDecimal result = BigDecimal.ZERO;

            for (Sale sale : c.getSales()) {
                BigDecimal bigDecimal = sale.getCar()
                        .getParts()
                        .stream()
                        .map(Part::getPrice)
                        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

                result = result.add(bigDecimal);
            }

            dto.setSpentMoney(result);

            return dto;
        }).collect(Collectors.toList());

        return new ListWithCustomerSellDto(collect);
    }
}
