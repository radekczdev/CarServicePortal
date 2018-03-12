package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    public Optional<Customer> getCustomer(final int id) {
        return customerRepository.findById(id);
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
