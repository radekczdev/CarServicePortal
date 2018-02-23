package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.RepairOrderGenerator;
import org.junit.Assert;
import org.junit.Test;

public class RepairOrderTestSuite {
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