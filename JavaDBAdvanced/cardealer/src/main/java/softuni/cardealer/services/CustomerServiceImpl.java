package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.CustomerRegisterDto;
import softuni.cardealer.domain.entites.Customer;
import softuni.cardealer.repositories.CustomerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
}
