package com.czajor.carserviceportal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADDRESSES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Address {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private int homeNumber;

    private int flatNumber = NO_FLAT_NUMBER;

    @NotNull
    private String postCode;

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
            return new Address(this);
        }
    }

    private Address(Builder builder) {
        this.city = builder.city;
        this.street = builder.street;
        this.homeNumber = builder.homeNumber;
        this.flatNumber = builder.flatNumber;
        this.postCode = builder.postCode;
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
