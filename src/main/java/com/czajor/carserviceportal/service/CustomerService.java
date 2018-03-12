package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.exception.CustomerNotFoundException;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        return Optional.ofNullable(customerRepository.findAll()).orElse(Collections.emptyList());
    }

    public Customer getCustomer(final int id) {
        try {
            return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            return new Customer();
        }
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public void addCar(int customerId, Car car) {
        try {
            Customer customer = getCustomer(customerId);
            customer.addCar(car);
            car.addCustomer(customer);
            customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeCar(int customerId, String licensePlate) {
        try {
            Customer customer = getCustomer(customerId);
            customer.removeCar(licensePlate);
            customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
