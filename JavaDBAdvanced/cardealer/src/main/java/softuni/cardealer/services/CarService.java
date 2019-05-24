package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.CarRegisterDto;
import softuni.cardealer.domain.entites.Car;

public interface CarService {
    String saveCar(CarRegisterDto dto);

    Car getCar(Integer id);
}
