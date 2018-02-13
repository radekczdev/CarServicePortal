package com.czajor.carserviceportal.customer;

import lombok.Data;

public @Data class Address {
    private final String city;
    private final String street;
    private final int homeNumber;
    private final int flatNumber;
    private final String postCode;
    private static final int NO_FLAT_NUMBER = 0;

    public static class Builder {
        private String city;
        private String street;
        private int homeNumber;
        private int flatNumber;
        private String postCode;

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder homeNumber(int homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public Builder flatNumber(int flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Address build(){
            return new Address(city, street, homeNumber, flatNumber, postCode);
        }
    }

    private Address(final String city, final String street, final int homeNumber, final int flatNumber, final String postCode) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.flatNumber = flatNumber;
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        String address;
        address = "\n         city: " + city +
                "\n         street: " + street +
                "\n         home number: " + homeNumber;
        if(flatNumber != NO_FLAT_NUMBER) {
            address += "\n         flat number: " + flatNumber;
        }
        address += "\n         post code: " + postCode;
        return address;
    }
}
