package com.czajor.carserviceportal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarDto {
    private String licensePlate;
    private String brand;
    private String model;
    private Integer buildYear;
    private String engine;
    private Double engineVolume;
}
