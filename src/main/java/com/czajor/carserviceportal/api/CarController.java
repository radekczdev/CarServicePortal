package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.mapper.CarMapper;
import com.czajor.carserviceportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{carId}")
    public CarDto getCar(@PathVariable("carId") final String carId) {
        return carMapper.mapToCarDto(carService.getCar(carId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void modifyCar(@RequestBody final CarDto carDto) {
        carService.modifyCar(carDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{licensePlate}")
    public void deleteCar(@PathVariable("licensePlate") final String licensePlate) {
        carService.deleteCar(licensePlate);
    }

}
