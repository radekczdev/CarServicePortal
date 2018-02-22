package com.czajor.carserviceportal.car;

import com.czajor.carserviceportal.RepairOrderGenerator;
import com.czajor.carserviceportal.customer.Address;
import com.czajor.carserviceportal.customer.AddressDao;
import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.RepairOrderHandler;
import com.czajor.carserviceportal.repairorder.RepairOrderStatus;
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
    @Autowired
    private AddressDao addressDao;

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

    @Test
    public void changeOrderStatus() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());
        RepairOrder order = orderHandler.getOrdersSet().get(0);

        System.out.println(order);

        // When
        order.changeStatus(RepairOrderStatus.QUEUE);
        order.changeStatus(RepairOrderStatus.WORKSHOP);
        order.changeStatus(RepairOrderStatus.READY);
        String currentOrderStatus = order.getCurrentStatus().getStatusName();
        int statusChangesNumber = order.getPreviousStatusList().size();

        for(RepairOrderStatus status : order.getPreviousStatusList()) {
            System.out.println(status);
        }

        // Then
        Assert.assertSame(RepairOrderStatus.READY,currentOrderStatus);
        Assert.assertEquals(3, statusChangesNumber);
    }
}
