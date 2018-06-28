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
@RequestMapping(path = "/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CustomerDto getCustomer(@PathVariable final int id) {
        return customerMapper.mapToCustomerDto(customerService.getCustomer(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapToCustomerDtoList(customerService.getCustomers());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody final CustomerDto customerDto) {
        return customerService.addCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void modifyCustomer(@RequestBody final CustomerDto customerDto) {
        customerService.modifyCustomer(customerDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteCustomer(@PathVariable final int id) {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{customerId}/car", consumes = APPLICATION_JSON_VALUE)
    public void addCar(@PathVariable final int customerId, @RequestBody final CarDto carDto) {
        customerService.addCar(customerId, carMapper.mapToCar(carDto));
    }

}
