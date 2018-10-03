package com.czajor.carserviceportal.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "REPAIR_ORDERS")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public final class RepairOrder {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
                    name = "REPAIR_ORDER_STATUS_HISTORY",
                    joinColumns = {
                            @JoinColumn(name = "repair_order_id", referencedColumnName = "id")
                    },
                    inverseJoinColumns = {
                            @JoinColumn(name = "previous_status_id", referencedColumnName = "id")
                    }
            )
    private List<RepairOrderStatus> previousStatusList = new ArrayList<>();

    @NotNull
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private RepairOrderStatus currentStatus;

    @NotNull
    @ElementCollection(targetClass = RepairOrderType.class, fetch = FetchType.EAGER)
    @JoinTable(name = "REPAIR_ORDER_TYPES", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<RepairOrderType> repairOrderType = new HashSet<>();

    @NotNull
    @OneToOne(
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Car car;

    @NotNull
    private String description;

    @NotNull
    private Date dateOfCreation;

    public RepairOrder(Car car, String description, Set<RepairOrderType> repairOrderTypes) {
        this.currentStatus = new RepairOrderStatus(StatusType.PREPARED);
        this.repairOrderType = repairOrderTypes;
        this.car = car;
        this.description = description;
        this.dateOfCreation = new Date();
    }

    public void changeStatus(StatusType status) throws IllegalArgumentException{
        if(!status.equals(currentStatus.getStatusType())) {
            currentStatus.closeStatus();
            previousStatusList.add(currentStatus);
            currentStatus = new RepairOrderStatus(status);
        } else {
            throw new IllegalArgumentException("Status already exists!");
        }
    }

    public void setCar (Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RepairOrder that = (RepairOrder) o;

        if (!currentStatus.equals(that.currentStatus)) return false;
        if (repairOrderType != null ? !repairOrderType.equals(that.repairOrderType) : that.repairOrderType != null)
            return false;
        if (!car.equals(that.car)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Order info: " +
                "\n     id: " + id +
                "\n     current status: " + currentStatus.getStatusType() +
                "\n     type: " + repairOrderType +
                "\n     description: " + description +
                "\n     created: " + dateOfCreation +
                "\n\nCustomer: " + car.getCustomer();
    }
}