package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.mapper.CarMapper;
import com.czajor.carserviceportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCar")
    public CarDto getCar(@RequestParam final String carId) {
        return carMapper.mapToCarDto(carService.getCar(carId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "modifyCar", consumes = APPLICATION_JSON_VALUE)
    public void modifyCar(@RequestBody final CarDto carDto) {
        carService.modifyCar(carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "modifyCarParameter", consumes = APPLICATION_JSON_VALUE)
    public void modifyCarParameter(@RequestBody final CarDto carDto) {
        carService.modifyCarParameter(carDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "deleteCar")
    public void deleteCar(@RequestParam final String licensePlate) {
        carService.deleteCar(licensePlate);
    }

}
