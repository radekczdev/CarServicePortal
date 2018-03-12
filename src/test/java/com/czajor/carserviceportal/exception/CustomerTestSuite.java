package com.czajor.carserviceportal.exception;

import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.repository.CustomerRepository;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import com.czajor.carserviceportal.model.Car;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        Customer customer = repairOrder.getCar().getCustomer();
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

