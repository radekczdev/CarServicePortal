package com.czajor.carserviceportal.domain;

import com.czajor.carserviceportal.model.RepairOrderType;
import com.czajor.carserviceportal.model.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    public RepairOrderSoapDto (int id, String carId, String description, List<RepairOrderType> repairOrderTypes, String statusType) {
        this.id = id;
        this.carId = carId;
        this.description = description;
//        this.repairOrderTypes = repairOrderTypes.stream().collect(Collectors.toList());
        this.currentStatus = statusType;
    }
}
