package com.czajor.carserviceportal.repairorder;

public enum RepairOrderStatus {
    READY("ready"),
    WORKSHOP("currently in workshop"),
    QUEUE("in queue"),
    PREPARED("to be confirmed");

    private String statusDescription;

    RepairOrderStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public static RepairOrderStatus getStatus(String statusDescription) {
        switch(statusDescription) {
            case "ready":
                return READY;
            case "currently in workshop":
                return WORKSHOP;
            case "in queue":
                return QUEUE;
            case "to be confirmed":
                return PREPARED;
            default:
                throw new IllegalArgumentException("Unknown status type: " + statusDescription);
        }
    }

}
