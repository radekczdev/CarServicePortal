package com.czajor.carserviceportal.car;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

}
