package com.czajor.carserviceportal.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.lang.String;

import static java.lang.String.valueOf;

@Entity
@Table(name = "REPAIR_ORDER_STATUSES")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class RepairOrderStatus {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private StatusType statusType;
    @NotNull
    private Date dateBegan;
    private Date dateEnded;

    public RepairOrderStatus(StatusType statusType) throws IllegalArgumentException {
        try {
            this.statusType = statusType;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown status type: " + statusType);
        }
        this.dateBegan = new Date();
    }

    public void closeStatus() {
        this.dateEnded = new Date();
    }

    @Override
    public String toString() {
        return valueOf(statusType).toLowerCase() +
                ", date began: " + dateBegan;
    }
}
