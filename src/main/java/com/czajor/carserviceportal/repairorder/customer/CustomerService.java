package com.czajor.carserviceportal.repairorder.customer;

import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.customer.Customer;
import com.czajor.carserviceportal.repairorder.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(final Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
//    public void addOrder(RepairOrder repairOrder) {
//        ordersSet.put(repairOrder.getId(), repairOrder);
//    }
//
//    public RepairOrder getOrder(int id) {
//        return ordersSet.get(id);
//    }
//
//    public Map<Integer, RepairOrder> getOrdersSet() {
//        return ordersSet;
//    }
}
