package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.StatusTypeDto;
import com.czajor.carserviceportal.exception.OrderNotFoundException;
import com.czajor.carserviceportal.mapper.StatusTypeMapper;
import com.czajor.carserviceportal.model.Mail;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.RepairOrderStatus;
import com.czajor.carserviceportal.model.StatusType;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderService {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private StatusTypeMapper statusTypeMapper;

    @Autowired
    private EmailService emailService;

    @Value(value = "${spring.mail.username}")
    private String serviceEmail;

    public RepairOrder createRepairOrder(RepairOrder repairOrder) {
        repairOrderRepository.save(repairOrder);
        emailService.run(new Mail(
                serviceEmail,
                repairOrder.getCar().getCustomer().getEmail(),
                "Repair order for car successfully created!",
                "Dear Customer!\nRepair order for car with license plates: " + repairOrder.getCar().getId() +
                        " have been successfully created.\n" + repairOrder.getCurrentStatus())
        );
        return repairOrder;
    }

    public void changeRepairOrderStatus(int id, StatusTypeDto statusTypeDto) {
        try {
            StatusType statusType = statusTypeMapper.mapToRepairOrderStatus(statusTypeDto);
            RepairOrder repairOrder = repairOrderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
            repairOrder.changeStatus(statusType);
            repairOrderRepository.save(repairOrder);
            emailService.run(new Mail(
                    serviceEmail,
                    repairOrder.getCar().getCustomer().getEmail(),
                    "Repair order for car changed status!",
                    "Dear Customer!\nRepair order for car with license plates: " + repairOrder.getCar().getId() +
                            " have changed status to:\n" + repairOrder.getCurrentStatus()
            ));
        } catch (Exception e) {
            System.out.println("Changing repair order status thrown exception: " + e);
        }
    }

    public List<RepairOrder> getRepairOrders() {
        return repairOrderRepository.findAll();
    }
}
