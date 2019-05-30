package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.json_import_dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() != 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/cars.json").getFile());
    }

    @Override
    public String importCars(String carsFileContent) {
        CarImportDto[] carImportDtos = this.gson.fromJson(carsFileContent, CarImportDto[].class);

        StringBuilder sb = new StringBuilder();

        for (CarImportDto carImportDto : carImportDtos) {
            if (!this.validationUtil.isValid(carImportDto)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car car = this.modelMapper.map(carImportDto, Car.class);
            Racer racer = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);

            car.setRacer(racer);

            this.carRepository.saveAndFlush(car);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    car.getClass().getSimpleName(),
                    String.format("%s %s @ %s", car.getBrand(), car.getModel(), car.getYearOfProduction())))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

}
