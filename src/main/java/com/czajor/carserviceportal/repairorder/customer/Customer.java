package com.czajor.carserviceportal.repairorder.customer;

import com.czajor.carserviceportal.repairorder.address.Address;
import com.czajor.carserviceportal.repairorder.car.Car;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Customer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    @OneToOne(
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Car> carList = new ArrayList<>();

    public Customer(String name, String surname, String email, String phoneNumber, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }

    @Override
    public String toString() {
        return  "\n     id: " + id +
                "\n     name: " + name +
                "\n     surname: " + surname +
                "\n     email: " + email +
                "\n     phoneNumber: " + phoneNumber +
                "\n     address: " + address +
                "\n     car: " + carList +
                "\n";
    }
}
