package com.czajor.carserviceportal;

import lombok.Data;

public @Data class Address {
    private final String city;
    private final String street;
    private final int homeNumber;
    private final int flatNumber;
    private final String postCode;
}
