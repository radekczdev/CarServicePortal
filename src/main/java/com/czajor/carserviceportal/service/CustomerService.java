package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.exception.CustomerNotFoundException;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repository.CarRepository;
import com.czajor.carserviceportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    public Customer addCustomer(final Customer customer) {
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

    public void deleteCustomer(final int id) {
        customerRepository.deleteById(id);
    }

    public List<Car> getCars() {
        return Optional.ofNullable(carRepository.findAll()).orElse(Collections.emptyList());
    }

    public Car getCar(final String carId) {
        return carRepository.findById(carId).orElse(new Car());
    }

    public void addCar(final int customerId, Car car) {
        try {
            Customer customer = getCustomer(customerId);
            customer.addCar(car);
            car.addCustomer(customer);
            customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyCar(final Car car) {
        String id = car.getId();
        car.addCustomer(carRepository.findById(id).get().getCustomer());
        carRepository.save(car);
    }

    public void deleteCar(final String licensePlate) {
        try {
            carRepository.deleteById(licensePlate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
