package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.model.StatusType;
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
    public void testRepairOrderRepository() throws Exception {
        // Given
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        Customer customer = repairOrder.getCar().getCustomer();
        Car car2 = new Car("WU12334", "brand", "model", 1995, "diesel",1.9);
        car2.addCustomer(customer);
        customer.addCar(car2);

        repairOrder.changeStatus(StatusType.QUEUE);
        repairOrder.changeStatus(StatusType.READY);

        // When
        repairOrderRepository.save(repairOrder);

        // Then
        int id = repairOrder.getId();
        int amountOfStatusChanges = repairOrder.getPreviousStatusList().size();
        RepairOrder repairOrderFromDb = repairOrderRepository.findById(id).orElse(new RepairOrder());
        Assert.assertEquals(id, repairOrderFromDb.getId());
        Assert.assertEquals(amountOfStatusChanges, repairOrderFromDb.getPreviousStatusList().size());

        // CleanUp
        repairOrderRepository.deleteAll();
    }

    @Test
    public void changeOrderStatus() {
        // Given
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        System.out.println(repairOrder);

        // When
        repairOrder.changeStatus(StatusType.QUEUE);
        repairOrder.changeStatus(StatusType.WORKSHOP);
        repairOrder.changeStatus(StatusType.READY);
        StatusType currentOrderStatus = repairOrder.getCurrentStatus().getStatusType();
        int statusChangesNumber = repairOrder.getPreviousStatusList().size();

        repairOrder.getPreviousStatusList().forEach(System.out::println);

        // Then
        Assert.assertSame(StatusType.READY,currentOrderStatus);
        Assert.assertEquals(3, statusChangesNumber);
    }
}