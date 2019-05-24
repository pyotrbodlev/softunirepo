package softuni.cardealer.domain.dtos;

import softuni.cardealer.domain.entites.Car;
import softuni.cardealer.domain.entites.Customer;

import javax.validation.constraints.NotNull;

public class SaleRegisterDto {
    private double discount;
    private Car car;
    private Customer customer;

    @NotNull
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @NotNull
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @NotNull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
