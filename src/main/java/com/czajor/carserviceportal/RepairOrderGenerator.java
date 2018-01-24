package com.czajor.carserviceportal;

import com.czajor.carserviceportal.customer.Address;
import com.czajor.carserviceportal.customer.Car;
import com.czajor.carserviceportal.customer.Customer;
import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.RepairOrderType;

public class RepairOrderGenerator {
    private Address address;
    private Car car;
    private RepairOrderType orderType;
    private String description;

    public void prepare() {
        address = new Address("Warszawa", "Al. Jerozolimskie", 154, 15, "10-010");
        car = new Car("FORD", "MONDEO", 2015, "diesel", 2.0);
        orderType = RepairOrderType.DAILY_SERVICE;
        description = "TODO: change oil and filters, check and change light bulbs";
    }

    public RepairOrder getOrder() {
        Customer customer = new Customer("John", "Doe", "j.doe@mail.com", "+48758421015", address, car);
        return new RepairOrder(orderType, customer, description);
    }
}
