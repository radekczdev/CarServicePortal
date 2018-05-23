package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.domain.CustomerDto;
import com.czajor.carserviceportal.mapper.CarMapper;
import com.czajor.carserviceportal.mapper.CustomerMapper;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "customers/{id}")
    public CustomerDto getCustomer(@PathVariable final int id) {
        return customerMapper.mapToCustomerDto(customerService.getCustomer(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "customers")
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapToCustomerDtoList(customerService.getCustomers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "customers", consumes = APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody final CustomerDto customerDto) {
        return customerService.addCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "customers", consumes = APPLICATION_JSON_VALUE)
    public void modifyCustomer(@RequestBody final CustomerDto customerDto) {
        customerService.addCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "customers/{id}")
    public void deleteCustomer(@PathVariable final int id) {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "customers/{customerId}/{carId}", consumes = APPLICATION_JSON_VALUE)
    public void addCar(@PathVariable final int customerId, @PathVariable final String carId) {
        customerService.addCar(customerId, carId);
    }

}
