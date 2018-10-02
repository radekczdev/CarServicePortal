package com.czajor.carserviceportal.domain;

import com.czajor.carserviceportal.model.RepairOrderStatus;
import com.czajor.carserviceportal.model.RepairOrderType;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepairOrderDto {
    private int id;
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
