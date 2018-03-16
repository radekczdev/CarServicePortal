package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderService {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    public RepairOrder createRepairOrder(RepairOrder repairOrder) {
        return repairOrderRepository.save(repairOrder);
    }

    public List<RepairOrder> getRepairOrders() {
        return repairOrderRepository.findAll();
    }
}
