package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.exception.CarNotFoundException;
import com.czajor.carserviceportal.mapper.CarMapper;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @GetMapping(value = "/{carId}")
    public CarDto getCar(@PathVariable("carId") final String carId) {
        return carMapper.mapToCarDto(carService.getCar(carId));
    }

    @GetMapping
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public CarDto modifyCar(@RequestBody final CarDto carDto, HttpServletResponse response) {
        try {
            Car car = carService.modifyCar(carDto);
            return carMapper.mapToCarDto(car);
        } catch (IllegalAccessException e1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Wrong DTO object provided!"
            );
        } catch (CarNotFoundException e2) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Car not found."
            );
        }
    }

    @DeleteMapping(value = "/{licensePlate}")
    public void deleteCar(@PathVariable("licensePlate") final String licensePlate) {
        carService.deleteCar(licensePlate);
    }

}
