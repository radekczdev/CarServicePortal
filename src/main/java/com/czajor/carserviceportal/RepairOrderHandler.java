package com.czajor.carserviceportal;

import com.czajor.carserviceportal.repairorder.RepairOrder;

import java.util.HashMap;
import java.util.Map;

public class RepairOrderHandler {
    private final Map<Integer, RepairOrder> ordersSet = new HashMap<>();

    public void addOrder(RepairOrder repairOrder) {
        ordersSet.put(repairOrder.getId(), repairOrder);
    }

    public RepairOrder getOrder(int id) {
        return ordersSet.get(id);
    }

    public Map<Integer, RepairOrder> getOrdersSet() {
        return ordersSet;
    }
}
