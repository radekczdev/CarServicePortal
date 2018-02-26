package com.czajor.carserviceportal.address;

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
public class AddressTestSuite {

    @Autowired
    private AddressRepository addressDao;

    @Test
    public void testAddressDao() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        Address address = orderHandler.getOrdersSet().get(0).getCar().getCustomer().getAddress();

        // When
        addressDao.save(address);

        // Then
        int id = address.getId();
        Address readAddressFromDb = addressDao.findOne(id);
        Assert.assertEquals(id, readAddressFromDb.getId());

        // CleanUp
        addressDao.deleteAll();
    }
}
