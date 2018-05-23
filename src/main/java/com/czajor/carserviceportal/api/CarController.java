package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.mapper.CarMapper;
import com.czajor.carserviceportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.GET, value = "cars/{carId}")
    public CarDto getCar(@PathVariable final String carId) {
        return carMapper.mapToCarDto(carService.getCar(carId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "cars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cars", consumes = APPLICATION_JSON_VALUE)
    public void modifyCar(@RequestBody final CarDto carDto) {
        carService.modifyCar(carDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cars/{licensePlate}")
    public void deleteCar(@PathVariable final String licensePlate) {
        carService.deleteCar(licensePlate);
    }

}
