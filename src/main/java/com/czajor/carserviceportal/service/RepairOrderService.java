package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.StatusTypeDto;
import com.czajor.carserviceportal.exception.OrderNotFoundException;
import com.czajor.carserviceportal.mapper.StatusTypeMapper;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.RepairOrderStatus;
import com.czajor.carserviceportal.model.StatusType;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderService {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private StatusTypeMapper statusTypeMapper;

    public RepairOrder createRepairOrder(RepairOrder repairOrder) {
        return repairOrderRepository.save(repairOrder);
    }

    public void changeRepairOrderStatus(int id, StatusTypeDto statusTypeDto) {
        try {
            StatusType statusType = statusTypeMapper.mapToRepairOrderStatus(statusTypeDto);
            RepairOrder repairOrder = repairOrderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
            repairOrder.changeStatus(statusType);
            repairOrderRepository.save(repairOrder);
        } catch (Exception e) {
            System.out.println("Changing repair order status thrown exception: " + e);
        }
    }

    public List<RepairOrder> getRepairOrders() {
        return repairOrderRepository.findAll();
    }
}
