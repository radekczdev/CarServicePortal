package com.czajor.carserviceportal.mapper;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.model.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getLicensePlate(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getBuildYear(),
                carDto.getEngine(),
                carDto.getEngineVolume()
        );
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getBuildYear(),
                car.getEngine(),
                car.getEngineVolume()
        );
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}
