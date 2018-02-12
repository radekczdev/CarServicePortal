package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.customer.Customer;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public @Data
class RepairOrder {
    private static int orderID = 0;
    private final int thisOrderID;
    private RepairOrderStatus status;
    private final RepairOrderType repairOrderType;
    private final Customer customer;
    private final String description;
    private final LocalDateTime dateOfCreation;
    private LocalDateTime dateOfReadyToCollect;

    public RepairOrder(RepairOrderType repairOrderType, Customer customer, String description) {
        this.thisOrderID = orderID++;
        this.status = new RepairOrderStatus(RepairOrderStatus.PREPARED);
        this.repairOrderType = repairOrderType;
        this.customer = customer;
        this.description = description;
        this.dateOfCreation = LocalDateTime.now(ZoneId.systemDefault());
    }

    public void changeStatus(String status) {
        this.status.changeState(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RepairOrder that = (RepairOrder) o;

        if (thisOrderID != that.thisOrderID) return false;
        if (!status.equals(that.status)) return false;
        if (repairOrderType != null ? !repairOrderType.equals(that.repairOrderType) : that.repairOrderType != null)
            return false;
        if (!customer.equals(that.customer)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        return thisOrderID;
    }

    @Override
    public String toString() {
        return "Order info: " +
                "\n     id: " + thisOrderID +
                "\n     status: " + status.getStatus() +
                "\n     type: " + repairOrderType.getTypeName() +
                "\n     description: " + description +
                "\n     created: " + dateOfCreation.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                "\n\nCustomer: " + customer;
    }
}