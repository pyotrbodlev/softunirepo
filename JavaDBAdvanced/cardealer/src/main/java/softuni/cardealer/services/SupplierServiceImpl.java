package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.ListWithSuppliersDto;
import softuni.cardealer.domain.dtos.SupplierRegisterDto;
import softuni.cardealer.domain.dtos.SupplierXmlDto;
import softuni.cardealer.domain.entites.Supplier;
import softuni.cardealer.repositories.SupplierRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveSupplier(SupplierRegisterDto dto){

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<SupplierRegisterDto>> constraintViolations = validator.validate(dto);

        if (constraintViolations.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<SupplierRegisterDto> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }

            return sb.toString();
        }

        Supplier supplier = this.modelMapper.map(dto, Supplier.class);

        this.supplierRepository.save(supplier);

        return dto.getName() + Text.SUCCESSFUL_REGISTER;
    }

    @Override
    public Supplier getSupplier(Integer id){
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public ListWithSuppliersDto getAllSuppliersThatDoNotImportParts(){
        List<SupplierXmlDto> dtos = this.supplierRepository.findAllNotImporter().stream().map(s -> {
            SupplierXmlDto dto = this.modelMapper.map(s, SupplierXmlDto.class);
            dto.setPartsCount(s.getPartList().size());
            return dto;
        }).collect(Collectors.toList());

        return new ListWithSuppliersDto(dtos);
    }
}
