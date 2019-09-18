package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.exception.CarNotFoundException;
import com.czajor.carserviceportal.mapper.SameFieldsMapper;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Optional.ofNullable;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private CarRepository carRepository;

    @Autowired
    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        return ofNullable(carRepository.findAll()).orElse(Collections.emptyList());
    }

    public Car getCar(final String carId) {
        return carRepository.findById(carId).orElse(new Car());
    }
    
    public Car addCar(final Car car) {
        return carRepository.save(car);
    }

    public Car modifyCar(final CarDto carDto) throws IllegalAccessException, CarNotFoundException {
        LOGGER.info("Starting to modifyCarParameters by CarService...");
        Car car = carRepository.findById(carDto.getLicensePlate()).orElseThrow(CarNotFoundException::new);
        car = (Car) SameFieldsMapper.map(car, carDto);
        return carRepository.save(car);
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
