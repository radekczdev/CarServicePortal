package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.car.Car;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public @Data
class RepairOrder {
    private static int orderID = 0;
    private final int thisOrderID;
    private List<RepairOrderStatus> previousStatusList = new ArrayList<>();
    private RepairOrderStatus currentStatus;
    private final Set<RepairOrderType> repairOrderType = new HashSet<>();
    private final Car car;
    private final String description;
    private final LocalDateTime dateOfCreation;
    private LocalDateTime dateOfReadyToCollect;

    public RepairOrder(Car car, String description, RepairOrderType... repairOrderType) {
        this.thisOrderID = orderID++;
        this.currentStatus = new RepairOrderStatus(RepairOrderStatus.PREPARED);
        this.repairOrderType.addAll(Arrays.asList(repairOrderType));
        this.car = car;
        this.description = description;
        this.dateOfCreation = LocalDateTime.now(ZoneId.systemDefault());
    }

    public void changeStatus(String status) {
        currentStatus.closeStatus();
        previousStatusList.add(currentStatus);
        currentStatus = new RepairOrderStatus(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RepairOrder that = (RepairOrder) o;

        if (thisOrderID != that.thisOrderID) return false;
        if (!currentStatus.equals(that.currentStatus)) return false;
        if (repairOrderType != null ? !repairOrderType.equals(that.repairOrderType) : that.repairOrderType != null)
            return false;
        if (!car.equals(that.car)) return false;
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
                "\n     current status: " + currentStatus.getStatus() +
                "\n     type: " + repairOrderType +
                "\n     description: " + description +
                "\n     created: " + dateOfCreation.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                "\n\nCustomer: " + car.getCustomer();
    }
}