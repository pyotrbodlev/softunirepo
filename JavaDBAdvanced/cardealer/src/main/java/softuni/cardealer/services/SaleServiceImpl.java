package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.CarSimpleDto;
import softuni.cardealer.domain.dtos.ListWithSalesDto;
import softuni.cardealer.domain.dtos.SaleRegisterDto;
import softuni.cardealer.domain.dtos.SaleXmlDto;
import softuni.cardealer.domain.entites.Part;
import softuni.cardealer.domain.entites.Sale;
import softuni.cardealer.repositories.SaleRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveSale(SaleRegisterDto dto){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<SaleRegisterDto>> constraintViolations = validator.validate(dto);

        if (constraintViolations.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<SaleRegisterDto> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }

            return sb.toString();
        }

        Sale sale = this.modelMapper.map(dto, Sale.class);

        this.saleRepository.save(sale);

        return "Sale" + Text.SUCCESSFUL_REGISTER;
    }

    @Override
    public ListWithSalesDto listWithSalesDto (){
        List<Sale> all = this.saleRepository.findAll();

        List<SaleXmlDto> saleXmlDtos = all.stream().map(s -> {
            SaleXmlDto saleXmlDto = this.modelMapper.map(s, SaleXmlDto.class);

            saleXmlDto.setCar(this.modelMapper.map(s.getCar(), CarSimpleDto.class));
            saleXmlDto.setCustomerName(s.getCustomer().getName());

            BigDecimal price = s.getCar().getParts()
                    .stream()
                    .map(Part::getPrice)
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

            saleXmlDto.setPrice(price);

            BigDecimal priceWithDiscount = price.multiply(BigDecimal.valueOf((100 - s.getDiscount()) / 100));

            saleXmlDto.setPriceWithDiscount(priceWithDiscount);

            return saleXmlDto;
        }).collect(Collectors.toList());

        return new ListWithSalesDto(saleXmlDtos);
    }
}
