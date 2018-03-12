package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CustomerDto;
import com.czajor.carserviceportal.exception.CustomerNotFoundException;
import com.czajor.carserviceportal.mapper.CustomerMapper;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repairorder.customer.*;
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

    @RequestMapping(method = RequestMethod.GET, value = "getCustomers")
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapToCustomerDtoList(customerService.getCustomers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveCustomer", consumes = APPLICATION_JSON_VALUE)
    public Customer saveCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerMapper.mapToCustomer(customerDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCustomer")
    public CustomerDto getCustomer(@RequestParam final int id) throws CustomerNotFoundException {
        return customerMapper.mapToCustomerDto(customerService.getCustomer(id).orElseThrow(CustomerNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCustomer")
    public void deleteCustomer(@RequestParam final int id) {
        customerService.deleteCustomer(id);
    }
}
