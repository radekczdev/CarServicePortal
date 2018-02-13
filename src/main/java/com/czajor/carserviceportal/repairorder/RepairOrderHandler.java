package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, RepairOrder> getOrdersSet() {
        return ordersSet;
    }

    public List<Customer> getCustomersList() {
        return customersList;
    }
}
