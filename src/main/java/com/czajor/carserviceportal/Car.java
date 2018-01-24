package com.czajor.carserviceportal;

import lombok.Data;

public @Data
class Car {
    // maybe brand and model should be as one? entity 1:N
    private final String brand;
    private final String model;
    private final int buildYear;
    private final String engine;
    private final double engineVolume;
}
