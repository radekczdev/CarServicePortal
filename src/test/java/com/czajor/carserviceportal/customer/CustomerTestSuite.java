package com.czajor.carserviceportal.customer;

import com.czajor.carserviceportal.RepairOrderGenerator;
import com.czajor.carserviceportal.address.Address;
import com.czajor.carserviceportal.address.AddressRepository;
import com.czajor.carserviceportal.car.Car;
import com.czajor.carserviceportal.car.CarRepository;
import com.czajor.carserviceportal.repairorder.RepairOrderHandler;
import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTestSuite {

    @Autowired
    private CarRepository carDao;
    @Autowired
    private AddressRepository addressDao;
    @Autowired
    private CustomerRepository customerDao;

    @Test
    public void testCustomerDao() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        Customer customer = orderHandler.getOrder(0).getCar().getCustomer();
        Car car = customer.getCarList().get(0);
        Car car2 = new Car("brand", "model", 1995, "diesel",1.9, "WU12334", customer);
        customer.addCar(car2);
        Address address = customer.getAddress();

        // When
        addressDao.save(address);
        customerDao.save(customer);
        carDao.save(car);
        carDao.save(car2);

        // Then
        int id = customer.getId();
        int amountOfCars = customer.getCarList().size();
        Customer readCustomerFromDb = customerDao.findOne(id);
        Hibernate.initialize(customer.getCarList());
        Assert.assertEquals(id, readCustomerFromDb.getId());
        Assert.assertEquals(amountOfCars, readCustomerFromDb.getCarList().size());

        // CleanUp
        customerDao.deleteAll();
    }
}

