package com.czajor.carserviceportal.customer;

import lombok.Data;

public @Data class Address {
    private final String city;
    private final String street;
    private final int homeNumber;
    private final int flatNumber; // create BUILDER - flat number is not necessary
    private final String postCode;

    @Override
    public String toString() {
        return  "\n         city: " + city +
                "\n         street: " + street +
                "\n         home number: " + homeNumber +
                "\n         flat number: " + flatNumber +
                "\n         post code: " + postCode;
    }
}
