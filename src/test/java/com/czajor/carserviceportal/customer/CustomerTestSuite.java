package com.czajor.carserviceportal.customer;

import com.czajor.carserviceportal.RepairOrderGenerator;
import com.czajor.carserviceportal.RepairOrderHandler;
import com.czajor.carserviceportal.car.Car;
import com.czajor.carserviceportal.car.CarRepository;
import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTestSuite {
    @Autowired
    private CustomerRepository customerDao;

    @Test
    @Transactional
    public void testCustomerDao() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderHandler.addOrder(orderGenerator.generateSampleOrder());

        Customer customer = orderHandler.getOrder(0).getCar().getCustomer();
        Car car2 = new Car("brand", "model", 1995, "diesel",1.9, "WU12334", customer);
        customer.addCar(car2);

        // When
        customerDao.save(customer);

        // Then
        int id = customer.getId();
        int amountOfCars = customer.getCarList().size();
        Customer readCustomerFromDb = customerDao.findOne(id);
        List<Car> carList = readCustomerFromDb.getCarList();
        Assert.assertEquals(id, readCustomerFromDb.getId());
        Assert.assertEquals(amountOfCars, carList.size());

        // CleanUp
        customerDao.deleteAll();
    }
}

