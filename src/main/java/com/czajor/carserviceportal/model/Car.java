package com.czajor.carserviceportal.model;

import com.czajor.carserviceportal.exception.CarHasOwnerException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARS")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Car {
    @Id
    @Column(name="id", unique = true)
    private String id;

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

    @ManyToOne
    @JoinTable(name = "cars_of_customer",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
    private Customer customer;

    public Car(String licensePlate, String brand, String model, int buildYear, String engine, double engineVolume) {
        this.id = licensePlate;
        this.brand = brand;
        this.model = model;
        this.buildYear = buildYear;
        this.engine = engine;
        this.engineVolume = engineVolume;
    }

    public void addCustomer (Customer customer) throws CarHasOwnerException {
        if(this.customer == null) {
            this.customer = customer;
        } else {
            throw new CarHasOwnerException();
        }
    }


    @Override
    public String toString() {
        return  brand + " " +
                model + " " +
                buildYear + " " +
                ", engine: " + engineVolume + " " +
                engine + ", license plate number: " + id;
    }
}
