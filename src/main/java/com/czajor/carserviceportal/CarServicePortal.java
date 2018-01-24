package com.czajor.carserviceportal;

import com.czajor.carserviceportal.repairorder.RepairOrderHandler;

public class CarServicePortal {
    public static void main(String[] args) {
        RepairOrderHandler orderHandler = new RepairOrderHandler();
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        orderGenerator.prepare();
        orderHandler.addOrder(orderGenerator.getOrder());

        System.out.println(orderHandler.getOrder(0));
    }
}
