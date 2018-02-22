package com.czajor.carserviceportal.repairorder;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class RepairOrderStatus {
    public static final String PREPARED = "to be confirmed";
    public static final String QUEUE = "in queue";
    public static final String WORKSHOP = "currently in workshop";
    public static final String READY = "ready";

    private String status;
    private final LocalDateTime dateBegan;
    private LocalDateTime dateEnded;

    RepairOrderStatus(String status) throws IllegalArgumentException{
        try {
            this.status = checkStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown status type: " + status);
        }
        this.dateBegan = LocalDateTime.now(ZoneId.systemDefault());
    }

    private String checkStatus(String status) throws IllegalArgumentException {
        switch(status) {
            case READY:
                return READY;
            case WORKSHOP:
                return WORKSHOP;
            case QUEUE:
                return QUEUE;
            case PREPARED:
                return PREPARED;
            default:
                throw new IllegalArgumentException("Unknown type of Order Status!");
        }
    }

    public String getStatusName() {
        return status;
    }

    public LocalDateTime getDateBegan() {
        return dateBegan;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    public void closeStatus() {
        this.dateEnded = LocalDateTime.now(ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        return "Status: " + status +
                ", date began: " + dateBegan +
                ", date ended: " + dateEnded;
    }
}
