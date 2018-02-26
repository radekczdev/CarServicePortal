package com.czajor.carserviceportal.repairorder;

import com.czajor.carserviceportal.car.Car;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable
            (
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
    @OneToOne
    private RepairOrderStatus currentStatus;

    @ElementCollection(targetClass = RepairOrderType.class)
    @JoinTable(name = "REPAIR_ORDER_TYPES", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<RepairOrderType> repairOrderType = new HashSet<>();

    @NotNull
    @OneToOne(orphanRemoval = true)
    private Car car;

    @NotNull
    private String description;

    @NotNull
    private Date dateOfCreation;

    public RepairOrder(Car car, String description, RepairOrderType... repairOrderType) {
        this.currentStatus = new RepairOrderStatus(StatusType.PREPARED);
        this.repairOrderType.addAll(Arrays.asList(repairOrderType));
        this.car = car;
        this.description = description;
        this.dateOfCreation = new Date();
    }

    public void changeStatus(StatusType status) {
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