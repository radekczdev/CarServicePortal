package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.exception.CustomerNotFoundException;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return Optional.ofNullable(customerRepository.findAll()).orElse(Collections.emptyList());
    }

    public Customer getCustomer(final int id) {
        try {
            LOGGER.info("Preparing to get customer...");
            return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        } catch (Exception e) {
            LOGGER.error("Getting customer thrown error: " + e + e.getMessage());
            return new Customer();
        }
    }

    public void deleteCustomer(final int id) {
        customerRepository.deleteById(id);
    }

    public void addCar(final int customerId, Car car) {
        try {
            LOGGER.info("Preparing to add car to customer...");
            Customer customer = getCustomer(customerId);
            customer.addCar(car);
            car.addCustomer(customer);
            customerRepository.save(customer);
        } catch (Exception e) {
            LOGGER.error("Adding car thrown error: " + e + e.getMessage());
        }
    }
}
