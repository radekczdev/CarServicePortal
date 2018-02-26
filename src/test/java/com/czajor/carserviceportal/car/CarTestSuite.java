package com.czajor.carserviceportal.car;

import com.czajor.carserviceportal.RepairOrderGenerator;
import com.czajor.carserviceportal.address.Address;
import com.czajor.carserviceportal.address.AddressRepository;
import com.czajor.carserviceportal.customer.Customer;
import com.czajor.carserviceportal.customer.CustomerRepository;
import com.czajor.carserviceportal.repairorder.RepairOrderHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTestSuite {
    @Autowired
    private CarRepository carDao;
    @Autowired
    private AddressRepository addressDao;
    @Autowired
    private CustomerRepository customerDao;

    @Test
    public void testCarDao() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        Customer customer = orderHandler.getOrder(0).getCar().getCustomer();
        Car car = customer.getCarList().get(0);
        Address address = customer.getAddress();

        // When
        addressDao.save(address);
        customerDao.save(customer);
        carDao.save(car);

        // Then
        int id = car.getId();
        Car readCarFromDb = carDao.findOne(id);
        Assert.assertEquals(id, readCarFromDb.getId());

        // CleanUp
        customerDao.deleteAll();
    }
}
