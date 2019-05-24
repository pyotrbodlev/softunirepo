package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.PartRegisterDto;
import softuni.cardealer.domain.entites.Part;
import softuni.cardealer.repositories.PartRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String savePart(PartRegisterDto dto){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<PartRegisterDto>> constraintViolations = validator.validate(dto);

        if (constraintViolations.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<PartRegisterDto> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }

            return sb.toString();
        }

        Part part = this.modelMapper.map(dto, Part.class);

        this.partRepository.save(part);

        return dto.getName() + Text.SUCCESSFUL_REGISTER;
    }

    @Override
    public Part getPart(Integer id){
        return this.partRepository.findById(id).orElse(null);
    }

    @Override
    public int getPartsCount(){
        return (int) this.partRepository.count();
    }

}
