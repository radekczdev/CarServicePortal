package com.czajor.carserviceportal;

import com.czajor.carserviceportal.customer.Address;
import com.czajor.carserviceportal.car.Car;
import com.czajor.carserviceportal.customer.Customer;
import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.RepairOrderType;

public class RepairOrderGenerator {
    private Car car;
    private RepairOrderType[] orderType = new RepairOrderType[2];
    private String description;

    public void prepare() {
        Address address = new Address.Builder()
                .city("Warszawa")
                .street("Al. Jerozolimskie")
                .homeNumber(154)
                .flatNumber(15)
                .postCode("10-010")
                .build();
        Customer customer = new Customer("John", "Doe", "j.doe@mail.com", "+48758421015", address);
        car = new Car("FORD", "MONDEO", 2015, "diesel", 2.0, "TK1234F", customer);
        orderType[0] = RepairOrderType.DAILY_SERVICE;
        orderType[1] = RepairOrderType.MECHANICAL;

        customer.addCar(car);

        description = "TODO: change oil and filters, check and change light bulbs";
    }

    public RepairOrder getOrder() {
        return new RepairOrder(car, description, orderType);
    }
}
