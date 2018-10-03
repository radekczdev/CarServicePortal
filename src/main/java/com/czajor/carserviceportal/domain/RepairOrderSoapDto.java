package com.czajor.carserviceportal.domain;

import com.czajor.carserviceportal.model.RepairOrderType;
import com.czajor.carserviceportal.model.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepairOrderSoapDto {
    private int id;
    private String carId;
    private String description;
    private List<RepairOrderType> repairOrderTypes;
    private String currentStatus;

    public RepairOrderSoapDto (int id, String carId, String description, Set<RepairOrderType> repairOrderTypes, String statusType) {
        this.id = id;
        this.carId = carId;
        this.description = description;
        this.repairOrderTypes = repairOrderTypes.isEmpty() ? Collections.emptyList() : new ArrayList<>(repairOrderTypes);
        this.currentStatus = statusType;
    }
}
