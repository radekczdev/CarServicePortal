package com.czajor.carserviceportal.repairorder.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();
}
