package mostwanted.service;

import mostwanted.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() != 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return null;
    }

    @Override
    public String importCars(String carsFileContent) {
        return null;
    }
}
