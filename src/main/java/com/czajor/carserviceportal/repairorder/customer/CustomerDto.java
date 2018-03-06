package com.czajor.carserviceportal.repairorder.customer;

import com.czajor.carserviceportal.repairorder.address.Address;
import com.czajor.carserviceportal.repairorder.car.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Address address;
    private List<Car> carList;
}