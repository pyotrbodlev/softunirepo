package softuni.cardealer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.cardealer.configuration.CustomRandom;
import softuni.cardealer.domain.dtos.*;
import softuni.cardealer.domain.entites.Car;
import softuni.cardealer.domain.entites.Customer;
import softuni.cardealer.domain.entites.Part;
import softuni.cardealer.domain.entites.Supplier;
import softuni.cardealer.parsers.JsonParser;
import softuni.cardealer.services.*;

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
    private final CustomerService customerService;
    private final SaleService saleService;
    private final JsonParser jsonParser;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, JsonParser jsonParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
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

            if (part == null) {
                System.out.println("Part with " + nextInt + " id not exist");
                continue;
            }

            parts.add(part);
        }

        CustomRandom.clearCache();
        return parts;
    }

    private void seedCustomers() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("files/customers.json").getFile());

        CustomerRegisterDto[] customerRegisterDtos = this.jsonParser.fromJson(file, CustomerRegisterDto[].class);

        for (CustomerRegisterDto customerRegisterDto : customerRegisterDtos) {
            System.out.println(this.customerService.saveCustomer(customerRegisterDto));
        }
    }

    private void seedSales() {
        double discount = getRandomDiscount();
        Customer customer = this.customerService.getCustomer(CustomRandom.nextInt(1, this.customerService.getCustomerCount()));
        CustomRandom.clearCache();

        if (customer.isYoungDriver()){
            discount += 5.00;
        }

        Car car = this.carService.getCar(CustomRandom.nextInt(1, this.carService.getCarCount()));

        SaleRegisterDto saleRegisterDto = new SaleRegisterDto();
        saleRegisterDto.setCar(car);
        saleRegisterDto.setCustomer(customer);
        saleRegisterDto.setDiscount(discount);

        System.out.println(this.saleService.saveSale(saleRegisterDto));
    }

    private double getRandomDiscount() {
        List<Double> discounts = List.of(0.00, 5.00, 10.00, 15.00, 20.00, 30.00, 40.00, 50.00);

        int i = CustomRandom.nextInt(0, discounts.size());
        CustomRandom.clearCache();

        return discounts.get(i);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
