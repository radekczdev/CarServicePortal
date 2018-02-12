package com.czajor.carserviceportal.repairorder;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class RepairOrderStatus {
    public static final String READY = "ready";
    public static final String WORKSHOP = "currently in workshop";
    public static final String QUEUE = "in queue";
    public static final String PREPARED = "to be confirmed";

    private String status;
    private LocalDateTime dateBegan;
    private LocalDateTime dateEnded;

    RepairOrderStatus(String status) {
        try {
            this.status = switchStatus(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown status type: " + status);
        }
        this.dateBegan = LocalDateTime.now(ZoneId.systemDefault());
    }

    private String switchStatus(String status) throws IllegalArgumentException {
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
                throw new IllegalArgumentException();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateBegan() {
        return dateBegan;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    public void changeState(String status) {
        try {
            this.status = switchStatus(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown status type: " + status);
        }
        this.dateEnded = LocalDateTime.now(ZoneId.systemDefault());
    }
}
