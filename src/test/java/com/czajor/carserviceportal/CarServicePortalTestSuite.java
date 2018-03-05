package com.czajor.carserviceportal;

import com.czajor.carserviceportal.repairorder.RepairOrder;
import com.czajor.carserviceportal.repairorder.customer.CustomerService;
import com.czajor.carserviceportal.repairorder.status.StatusType;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import org.junit.Assert;
import org.junit.Test;

public class CarServicePortalTestSuite {
    @Test
    public void testCreateOrder() {
        // Given
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        System.out.println(repairOrder);

        // When
        repairOrder.changeStatus(StatusType.QUEUE);
        repairOrder.changeStatus(StatusType.READY);
        StatusType currentStatus = repairOrder.getCurrentStatus().getStatusType();

        System.out.println(repairOrder);

        // Then
        Assert.assertEquals(2, repairOrder.getPreviousStatusList().size());
        Assert.assertEquals(StatusType.READY, currentStatus);
    }
}
