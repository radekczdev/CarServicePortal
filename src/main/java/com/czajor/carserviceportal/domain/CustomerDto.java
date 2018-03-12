package com.czajor.carserviceportal.domain;

import com.czajor.carserviceportal.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Address address;
    private List<CarDto> carList;
}
