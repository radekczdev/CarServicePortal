package com.czajor.carserviceportal.repairorder.car;

import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.repository.CarRepository;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.repository.CustomerRepository;
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
    private CustomerRepository customerDao;

    @Test
    public void testCarDao() {
        // Given
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        Customer customer = repairOrder.getCar().getCustomer();
        Car car = customer.getCarList().get(0);

        // When
        carDao.save(car);

        // Then
        String id = car.getId();
        Car readCarFromDb = carDao.findById(id).orElse(new Car());
        Assert.assertEquals(id, readCarFromDb.getId());

        // CleanUp
        customerDao.deleteAll();
    }
}
