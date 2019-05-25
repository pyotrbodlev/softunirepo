package softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.configuration.Text;
import softuni.cardealer.domain.dtos.CarRegisterDto;
import softuni.cardealer.domain.dtos.CarXmlDto;
import softuni.cardealer.domain.dtos.ListWithCarsDto;
import softuni.cardealer.domain.dtos.PartXmlDto;
import softuni.cardealer.domain.entites.Car;
import softuni.cardealer.repositories.CarRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveCar(CarRegisterDto dto){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CarRegisterDto>> constraintViolations = validator.validate(dto);

        if (constraintViolations.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<CarRegisterDto> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }

            return sb.toString();
        }

        Car car = this.modelMapper.map(dto, Car.class);

        this.carRepository.save(car);

        return dto.getModel() + Text.SUCCESSFUL_REGISTER;
    }

    @Override
    public Car getCar(Integer id){
        return this.carRepository.findById(id).orElse(null);
    }

    @Override
    public int getCarCount(){
        return (int) this.carRepository.count();
    }

    @Override
    public ListWithCarsDto carsByToyota(){
        List<CarXmlDto> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota").stream().map(c -> this.modelMapper.map(c, CarXmlDto.class)).collect(Collectors.toList());

        return new ListWithCarsDto(toyota);
    }

    @Override
    public ListWithCarsDto getAllCarsWithParts(){
        List<CarXmlDto> dtos = this.carRepository.findAll().stream().map(c -> {
            CarXmlDto map = this.modelMapper.map(c, CarXmlDto.class);
            map.setParts(c.getParts().stream().map(p -> this.modelMapper.map(p, PartXmlDto.class)).collect(Collectors.toList()));
            return map;
        }).collect(Collectors.toList());

        return new ListWithCarsDto(dtos);
    }
}
