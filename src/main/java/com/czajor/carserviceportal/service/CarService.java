package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Optional.ofNullable;

@Service
public class CarService {

    private final Logger LOGGER = LoggerFactory.getLogger(Car.class);

    @Autowired
    private CarRepository carRepository;


    public List<Car> getCars() {
        return ofNullable(carRepository.findAll()).orElse(Collections.emptyList());
    }

    public Car getCar(final String carId) {
        return carRepository.findById(carId).orElse(new Car());
    }

    public void modifyCar(final Car car) {
        LOGGER.info("Starting to modifyCar by CarService...");
        String id = car.getId();
        try {
            ofNullable(carRepository.findById(id).get()).ifPresent(c -> car.addCustomer(c.getCustomer()));
            carRepository.save(car);
        } catch (NoSuchElementException e) {
            LOGGER.error("modifyCar thrown message: " + e.getMessage());
        }
    }

    public void deleteCar(final String licensePlate) {
        LOGGER.info("Starting to deleteCar by CarService...");
        try {
            carRepository.deleteById(licensePlate);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("deleteCar thrown message: " + e.getMessage());
        }
    }
}
