package com.czajor.carserviceportal.repairorder.car;

import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import com.czajor.carserviceportal.repairorder.customer.Customer;
import com.czajor.carserviceportal.repairorder.customer.CustomerRepository;
import com.czajor.carserviceportal.repairorder.customer.CustomerService;
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
        int id = car.getId();
        Car readCarFromDb = carDao.findOne(id);
        Assert.assertEquals(id, readCarFromDb.getId());

        // CleanUp
        customerDao.deleteAll();
    }
}
