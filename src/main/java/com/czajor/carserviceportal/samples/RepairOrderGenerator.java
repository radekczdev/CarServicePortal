package com.czajor.carserviceportal.samples;

import com.czajor.carserviceportal.model.Address;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.RepairOrderType;

import java.util.HashSet;

public class RepairOrderGenerator {
    public RepairOrder generateSampleOrder() {
        Address address = new Address.Builder()
                .city("Warszawa")
                .street("Al. Jerozolimskie")
                .homeNumber(154)
                .flatNumber(15)
                .postCode("10-010")
                .build();
        Customer customer = new Customer("John", "Doe", "j.doe@mail.com", "+48758421015", address);
        Car car = new Car("TK1234F", "FORD", "MONDEO", 2015, "diesel", 2.0);
        try {
            car.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("Adding customer for car thrown: " + e.getMessage());
        }
        customer.addCar(car);

        String description = "TODO: change oil and filters, check and change light bulbs";
        return new RepairOrder(
                car,
                description,
                new HashSet<RepairOrderType>() {{
                    add(RepairOrderType.DAILY_SERVICE);
                    add(RepairOrderType.MECHANICAL);
                }}
                );
    }

}
