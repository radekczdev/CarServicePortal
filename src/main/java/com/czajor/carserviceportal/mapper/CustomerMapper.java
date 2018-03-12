package com.czajor.carserviceportal.mapper;

import com.czajor.carserviceportal.domain.CustomerDto;
import com.czajor.carserviceportal.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    @Autowired
    private CarMapper carMapper;

    public Customer mapToCustomer(final CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getAddress(),
                null
        );
    }

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                carMapper.mapToCarDtoList(customer.getCarList())
        );
    }

    public List<CustomerDto> mapToCustomerDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }
}
