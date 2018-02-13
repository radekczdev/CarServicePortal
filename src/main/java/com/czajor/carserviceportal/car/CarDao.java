package com.czajor.carserviceportal.car;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CarDao extends CrudRepository<Car, Integer> {

}
