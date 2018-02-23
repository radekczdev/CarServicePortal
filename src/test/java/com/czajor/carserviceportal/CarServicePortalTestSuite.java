package com.czajor.carserviceportal;

import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.RepairOrderHandler;
import com.czajor.carserviceportal.repairorder.RepairOrderStatus;
import com.czajor.carserviceportal.repairorder.StatusType;
import org.junit.Assert;
import org.junit.Test;

public class CarServicePortalTestSuite {
    @Test
    public void testCreateOrder() {
        // Given
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());
        RepairOrder order = orderHandler.getOrdersSet().get(0);

        System.out.println(order);

        // When
        order.changeStatus(StatusType.QUEUE);
        order.changeStatus(StatusType.READY);
        StatusType currentStatus = order.getCurrentStatus().getStatusType();

        System.out.println(order);

        // Then
        Assert.assertEquals(2, order.getPreviousStatusList().size());
        Assert.assertEquals(StatusType.READY, currentStatus);
    }
}
