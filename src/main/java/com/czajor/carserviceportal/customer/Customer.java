package com.czajor.carserviceportal.customer;

import lombok.Data;

public @Data
final class Customer {
    private static int currentId = 0;
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final Address address;
    private final Car car;

    public Customer(String name, String surname, String email, String phoneNumber, Address address, Car car) {
        this.id = currentId++;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.car = car;
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
