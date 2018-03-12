package com.czajor.carserviceportal.repairorder.customer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
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
                customer.getAddress()
        );
    }

    public List<CustomerDto> mapToCustomerDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(c -> new CustomerDto(
                        c.getId(),
                        c.getName(),
                        c.getSurname(),
                        c.getEmail(),
                        c.getPhoneNumber(),
                        c.getAddress()
                ))
                .collect(Collectors.toList());
    }
}
