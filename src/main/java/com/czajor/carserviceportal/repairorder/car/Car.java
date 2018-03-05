package com.czajor.carserviceportal.repairorder.car;

import com.czajor.carserviceportal.repairorder.customer.Customer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARS")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Car {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true)
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
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "cars_of_customer",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
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

    @Override
    public String toString() {
        return  brand + " " +
                model + " " +
                buildYear + " " +
                ", engine: " + engineVolume + " " +
                engine + ", license plate number: " + licensePlate;
    }
}
