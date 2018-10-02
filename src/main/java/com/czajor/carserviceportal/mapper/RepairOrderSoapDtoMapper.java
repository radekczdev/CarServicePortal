package com.czajor.carserviceportal.mapper;

import com.czajor.carserviceportal.domain.RepairOrderSoapDto;
import com.czajor.carserviceportal.model.RepairOrder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepairOrderSoapDtoMapper {
    public RepairOrderSoapDto mapToRepairOrderSoapDto(RepairOrder repairOrder) {
        return new RepairOrderSoapDto(
                repairOrder.getId(),
                repairOrder.getCar().getId(),
                repairOrder.getDescription(),
                repairOrder.getRepairOrderType().stream().collect(Collectors.toList()),
                repairOrder.getCurrentStatus().getStatusType().name());
    }

    public List<RepairOrderSoapDto> mapToRepairOrderSoapDtoList(List<RepairOrder> repairOrderList) {
        return repairOrderList.stream().map(this::mapToRepairOrderSoapDto).collect(Collectors.toList());
    }
}
