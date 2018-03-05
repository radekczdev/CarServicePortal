package com.czajor.carserviceportal;

import com.czajor.carserviceportal.address.Address;
import com.czajor.carserviceportal.car.Car;
import com.czajor.carserviceportal.customer.Customer;
import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.RepairOrderType;

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
        Car car = new Car("FORD", "MONDEO", 2015, "diesel", 2.0, "TK1234F", customer);
        customer.addCar(car);

        String description = "TODO: change oil and filters, check and change light bulbs";
        return new RepairOrder(
                car,
                description,
                RepairOrderType.DAILY_SERVICE, RepairOrderType.MECHANICAL);
    }

}
