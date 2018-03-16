package com.czajor.carserviceportal.mapper;

import com.czajor.carserviceportal.domain.RepairOrderDto;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class RepairOrderMapper {

    @Autowired
    private CarService carService;

    public RepairOrder mapToRepairOrder(final RepairOrderDto repairOrderDto) {
        return new RepairOrder(
                carService.getCar(repairOrderDto.getCarId()),
                repairOrderDto.getDescription(),
                ofNullable(new HashSet<>(repairOrderDto.getRepairOrderTypes())).orElse(new HashSet<>())
        );
    }

    public RepairOrderDto mapToRepairOrderDto(final RepairOrder repairOrder) {
        return new RepairOrderDto(
                repairOrder.getCar().getId(),
                repairOrder.getDescription(),
                repairOrder.getRepairOrderType(),
                repairOrder.getCurrentStatus(),
                repairOrder.getDateOfCreation()
        );
    }

    public List<RepairOrderDto> mapToRepairOrderDtoList(List<RepairOrder> repairOrderList) {
        return repairOrderList.stream().map(this::mapToRepairOrderDto).collect(Collectors.toList());
    }
}
