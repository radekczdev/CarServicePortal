package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.repairorder.customer.CustomerDto;
import com.czajor.carserviceportal.repairorder.customer.CustomerMapper;
import com.czajor.carserviceportal.repairorder.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customer")
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
    public void saveCustomer(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerMapper.mapToCustomer(customerDto));
    }
}
