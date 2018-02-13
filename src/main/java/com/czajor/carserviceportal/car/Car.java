package com.czajor.carserviceportal.car;

import com.czajor.carserviceportal.customer.Customer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARS")
public final @Data
@Setter(AccessLevel.PRIVATE)
class Car {
    @Id
    @GeneratedValue
    @Column(name="ID", unique = true)
    private int id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private int buildYear;
    @NotNull
    private String engine;
    @NotNull
    private double engineVolume;
    @NotNull
    private String licensePlate;
    @NotNull
    @Transient
//    @ManyToOne
    private Customer customer;

    public Car(String brand, String model, int buildYear, String engine, double engineVolume, String licensePlate, Customer customer) {
        this.brand = brand;
        this.model = model;
        this.buildYear = buildYear;
        this.engine = engine;
        this.engineVolume = engineVolume;
        this.licensePlate = licensePlate;
        this.customer = customer;
    }

    public Car(){

    }

    @Override
    public String toString() {
        return  brand + " " +
                model + " " +
                buildYear + " " +
                ", engine: " + engineVolume + " " +
                engine + ", license plate number: " + licensePlate;
    }
}
