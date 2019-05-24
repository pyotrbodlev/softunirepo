package softuni.cardealer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.cardealer.configuration.CustomRandom;
import softuni.cardealer.domain.dtos.CarRegisterDto;
import softuni.cardealer.domain.dtos.PartRegisterDto;
import softuni.cardealer.domain.dtos.SupplierRegisterDto;
import softuni.cardealer.domain.entites.Part;
import softuni.cardealer.domain.entites.Supplier;
import softuni.cardealer.parsers.JsonParser;
import softuni.cardealer.services.CarService;
import softuni.cardealer.services.PartService;
import softuni.cardealer.services.SupplierService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CarDealerController implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final JsonParser jsonParser;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, JsonParser jsonParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    private void seedSuppliers() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("files/suppliers.json").getFile());

        SupplierRegisterDto[] supplierRegisterDtos = this.jsonParser.fromJson(file, SupplierRegisterDto[].class);

        for (SupplierRegisterDto supplierRegisterDto : supplierRegisterDtos) {
            System.out.println(this.supplierService.saveSupplier(supplierRegisterDto));
        }
    }

    private void seedParts() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("files/parts.json").getFile());
        PartRegisterDto[] partRegisterDtos = this.jsonParser.fromJson(file, PartRegisterDto[].class);
        Random random = new Random();

        for (PartRegisterDto partRegisterDto : partRegisterDtos) {
            int i = random.nextInt(15);
            int d = random.nextInt(15);
            int id = i + d;

            Supplier supplier = this.supplierService.getSupplier(id);
            partRegisterDto.setSupplier(supplier);

            System.out.println(this.partService.savePart(partRegisterDto));
        }
    }

    private void seedCars() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("files/cars.json").getFile());

        CarRegisterDto[] carRegisterDtos = this.jsonParser.fromJson(file, CarRegisterDto[].class);

        for (CarRegisterDto carRegisterDto : carRegisterDtos) {
            carRegisterDto.setParts(getRandomParts());
            System.out.println(this.carService.saveCar(carRegisterDto));
        }
    }

    private List<Part> getRandomParts() {
        int i = CustomRandom.nextInt(10, 20);
        CustomRandom.clearCache();

        List<Part> parts = new ArrayList<>();

        for (int j = 0; j < i; j++) {
            int nextInt = CustomRandom.nextInt(1, this.partService.getPartsCount());
            Part part = this.partService.getPart(nextInt);

            if (part == null){
                System.out.println("Part with " + nextInt + " id not exist");
                continue;
            }

            parts.add(part);
        }

        CustomRandom.clearCache();
        return parts;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
