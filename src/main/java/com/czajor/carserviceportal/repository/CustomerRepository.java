package com.czajor.carserviceportal.repository;

import com.czajor.carserviceportal.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Integer id);

    @Override
    Customer save(Customer customer);
}
