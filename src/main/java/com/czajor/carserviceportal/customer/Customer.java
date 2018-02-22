package com.czajor.carserviceportal.customer;

import com.czajor.carserviceportal.car.Car;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "CUSTOMERS")
public @Data
@Setter(AccessLevel.PRIVATE)
final class Customer {
    private static int currentId = 0;
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final Address address;
    private final List<Car> car = new ArrayList<>();

    public Customer(String name, String surname, String email, String phoneNumber, Address address) {
        this.id = currentId++;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void addCar(Car car) {
        this.car.add(car);
    }

    @Override
    public String toString() {
        return  "\n     id: " + id +
                "\n     name: " + name +
                "\n     surname: " + surname +
                "\n     email: " + email +
                "\n     phoneNumber: " + phoneNumber +
                "\n     address: " + address +
                "\n     car: " + car +
                "\n";
    }
}
