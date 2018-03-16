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
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCustomer")
    public CustomerDto getCustomer(@RequestParam final int id) {
        return customerMapper.mapToCustomerDto(customerService.getCustomer(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCustomers")
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapToCustomerDtoList(customerService.getCustomers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCustomer", consumes = APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody final CustomerDto customerDto) {
        return customerService.addCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "modifyCustomer", consumes = APPLICATION_JSON_VALUE)
    public void modifyCustomer(@RequestBody final CustomerDto customerDto) {
        customerService.addCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCustomer")
    public void deleteCustomer(@RequestParam final int id) {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCar")
    public CarDto getCar(@RequestParam final String carId) {
        return carMapper.mapToCarDto(customerService.getCar(carId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(customerService.getCars());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCar", consumes = APPLICATION_JSON_VALUE)
    public void addCar(@RequestParam final int customerId, @RequestBody final CarDto carDto) {
        customerService.addCar(customerId, carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "modifyCar", consumes = APPLICATION_JSON_VALUE)
    public void modifyCar(@RequestBody final CarDto carDto) {
        customerService.modifyCar(carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "deleteCar")
    public void deleteCar(@RequestParam final String licensePlate) {
        customerService.deleteCar(licensePlate);
    }
}
