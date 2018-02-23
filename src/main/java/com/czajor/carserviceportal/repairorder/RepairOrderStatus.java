package com.czajor.carserviceportal.repairorder;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class RepairOrderStatus {
    private StatusType statusType;
    private final LocalDateTime dateBegan;
    private LocalDateTime dateEnded;

    RepairOrderStatus(StatusType statusType) throws IllegalArgumentException{
        try {
            this.statusType = statusType;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown status type: " + statusType);
        }
        this.dateBegan = LocalDateTime.now(ZoneId.systemDefault());
    }

    public void closeStatus() {
        this.dateEnded = LocalDateTime.now(ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        return "Status: " + statusType +
                ", date began: " + dateBegan +
                ", date ended: " + dateEnded;
    }
}
