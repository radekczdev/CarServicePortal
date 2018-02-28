package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.RepairOrderGenerator;
import com.czajor.carserviceportal.RepairOrderHandler;
import com.czajor.carserviceportal.car.Car;
import com.czajor.carserviceportal.customer.Customer;
import com.czajor.carserviceportal.repairorder.status.RepairOrderStatus;
import com.czajor.carserviceportal.repairorder.status.StatusType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepairOrderTestSuite {
    @Autowired
    RepairOrderRepository repairOrderRepository;

    @Test
    public void testRepairOrderRepository() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        Customer customer = orderHandler.getOrder(0).getCar().getCustomer();
        Car car2 = new Car("brand", "model", 1995, "diesel",1.9, "WU12334", customer);
        customer.addCar(car2);
        RepairOrder repairOrder = orderHandler.getOrder(0);

        repairOrder.changeStatus(StatusType.QUEUE);
        repairOrder.changeStatus(StatusType.READY);

        // When
        repairOrderRepository.save(repairOrder);

        // Then
        int id = repairOrder.getId();
        int amountOfStatusChanges = repairOrder.getPreviousStatusList().size();
        RepairOrder repairOrderFromDb = repairOrderRepository.findOne(id);
        Assert.assertEquals(id, repairOrderFromDb.getId());
        Assert.assertEquals(amountOfStatusChanges, repairOrderFromDb.getPreviousStatusList().size());

        // CleanUp
        repairOrderRepository.deleteAll();
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
        order.changeStatus(StatusType.QUEUE);
        order.changeStatus(StatusType.WORKSHOP);
        order.changeStatus(StatusType.READY);
        StatusType currentOrderStatus = order.getCurrentStatus().getStatusType();
        int statusChangesNumber = order.getPreviousStatusList().size();

        for(RepairOrderStatus status : order.getPreviousStatusList()) {
            System.out.println(status);
        }

        // Then
        Assert.assertSame(StatusType.READY,currentOrderStatus);
        Assert.assertEquals(3, statusChangesNumber);
    }
}