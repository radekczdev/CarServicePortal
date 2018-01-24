package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.customer.Customer;

import java.util.*;

public class RepairOrderHandler {
    private final Map<Integer, RepairOrder> ordersSet = new HashMap<>();
    private final List<Customer> customersList = new ArrayList<>();

    public void addOrder(RepairOrder repairOrder) {
        ordersSet.put(repairOrder.getThisOrderID(), repairOrder);
    }

    public boolean addCustomer(Customer customer) {
        return customersList.add(customer);
    }

    public RepairOrder getOrder(int id) {
        return ordersSet.get(id);
    }
}
