package com.czajor.carserviceportal.domain;

import com.czajor.carserviceportal.model.RepairOrderStatus;
import com.czajor.carserviceportal.model.RepairOrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RepairOrderDto {
    private String carId;
    private String description;
    private Set<RepairOrderType> repairOrderTypes;
    private RepairOrderStatus currentStatus;
    private Date dateOfCreation;


    public RepairOrderDto (String carId, String description, Set<RepairOrderType> repairOrderTypes) {
        this.carId = carId;
        this.description = description;
        this.repairOrderTypes = repairOrderTypes;
    }
}
