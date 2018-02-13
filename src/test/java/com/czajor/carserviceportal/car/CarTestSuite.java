package com.czajor.carserviceportal.car;

import com.czajor.carserviceportal.RepairOrderGenerator;
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
    private CarDao carDao;

    @Test
    public void testCarDao() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        Car car = orderHandler.getOrdersSet().get(0).getCar();

        // When
        carDao.save(car);

        // Then
        int id = car.getId();
        Car readCarFromDb = carDao.findOne(id);
        Assert.assertEquals(id, readCarFromDb.getId());

        // CleanUp
        carDao.deleteAll();
    }
}
