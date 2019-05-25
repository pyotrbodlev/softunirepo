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
import softuni.cardealer.parsers.XmlParser;
import softuni.cardealer.services.*;

import javax.xml.bind.JAXBException;
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
    private final XmlParser xmlParser;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, JsonParser jsonParser, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
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

    private void seedData() throws FileNotFoundException {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        for (int i = 0; i < 200; i++) {
            this.seedSales();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.getSalesWithAppliedDiscount();
    }

    /**
     * Query 1 – Ordered Customers
     * @throws JAXBException
     */
    public void getAllCustomersOrderByAge() throws JAXBException {
        ListWithCustomersDto listWithCustomersDto = this.customerService.getListWithCustomersOrderedByBirthDate();

        String s = this.xmlParser.toXml(listWithCustomersDto, ListWithCustomersDto.class);

        System.out.println(s);
    }

    /**
     * Query 2 – Cars from make Toyota
     * @throws JAXBException
     */
    public void getToyotaCars() throws JAXBException {
        ListWithCarsDto listWithCarsDto = this.carService.carsByToyota();

        String s = this.xmlParser.toXml(listWithCarsDto, ListWithCarsDto.class);

        System.out.println(s);
    }

    /**
     * Query 3 – Local Suppliers
     * @throws JAXBException
     */
    public void getAllNotImportedSuppliers() throws JAXBException {
        ListWithSuppliersDto allSuppliersThatDoNotImportParts = this.supplierService.getAllSuppliersThatDoNotImportParts();

        System.out.println(this.xmlParser.toXml(allSuppliersThatDoNotImportParts, ListWithSuppliersDto.class));
    }

    /**
     * Query 4 – Cars with Their List of Parts
     */
    public void getAllCars() throws JAXBException {
        ListWithCarsDto cars = this.carService.getAllCarsWithParts();

        System.out.println(this.xmlParser.toXml(cars, ListWithCarsDto.class));
    }

    /**
     * Query 5 – Total Sales by Customer
     * @throws JAXBException
     */
    public void getTotalSalesByCustomer() throws JAXBException {
        ListWithCustomerSellDto listWithCustomerSellDto = this.customerService.getListWithCustomerSellDto();

        System.out.println(this.xmlParser.toXml(listWithCustomerSellDto, ListWithCustomerSellDto.class));
    }

    /**
     * Query 6 – Sales with Applied Discount
     */
    public void getSalesWithAppliedDiscount() throws JAXBException {
        ListWithSalesDto listWithSalesDto = this.saleService.listWithSalesDto();

        System.out.println(this.xmlParser.toXml(listWithSalesDto, ListWithSalesDto.class));
    }
}
