package com.czajor.carserviceportal;

import lombok.Data;

import java.util.List;

public @Data
class Customer {
    private static int currentId = 0;
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final Address address;
    private final List<Car> car;

    public Customer(int id, String name, String surname, String email, String phoneNumber, Address address, List<Car> car) {
        this.id = currentId++;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.car = car;
    }
}
