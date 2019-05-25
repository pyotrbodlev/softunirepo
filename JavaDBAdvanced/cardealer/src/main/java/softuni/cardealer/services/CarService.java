package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.CarRegisterDto;
import softuni.cardealer.domain.dtos.ListWithCarsDto;
import softuni.cardealer.domain.entites.Car;

public interface CarService {
    String saveCar(CarRegisterDto dto);

    Car getCar(Integer id);

    int getCarCount();

    ListWithCarsDto carsByToyota();

    ListWithCarsDto getAllCarsWithParts();
}
