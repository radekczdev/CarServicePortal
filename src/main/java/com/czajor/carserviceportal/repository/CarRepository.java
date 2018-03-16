package com.czajor.carserviceportal.repository;

import com.czajor.carserviceportal.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {

    @Override
    List<Car> findAll();
}
