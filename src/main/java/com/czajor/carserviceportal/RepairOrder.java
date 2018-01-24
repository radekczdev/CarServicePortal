package com.czajor.carserviceportal;

import lombok.Data;

public @Data
class RepairOrder {
    private final static String STATUS_READY = "ready";
    private final static String STATUS_WORKSHOP = "currently in workshop";
    private final static String STATUS_QUEUE = "in queue";
    private final static String STATUS_PREPARED = "to be confirmed";

    private final static String TYPE_MECHANICAL = "mechanical";
    private final static String TYPE_ELECTRICAL = "electrical";
    private final static String TYPE_CLEANING = "cleaning";
    private final static String TYPE_DAILY_SERVICE = "daily service";

    private static int orderID = 0;
    private final int repairOrderID;
    private final String status;
    private final String repairOrderType;
    private final Customer customer;
    private final String description;

    public RepairOrder(int repairOrderID, String status, String repairOrderType, Customer customer, String description) {
        this.repairOrderID = orderID++;
        this.status = status;
        this.repairOrderType = repairOrderType;
        this.customer = customer;
        this.description = description;
    }
}