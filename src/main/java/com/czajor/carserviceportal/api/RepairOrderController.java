package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.RepairOrderDto;
import com.czajor.carserviceportal.mapper.RepairOrderMapper;
import com.czajor.carserviceportal.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/repairOrder")
@CrossOrigin(origins = "*")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createRepairOrder", consumes = APPLICATION_JSON_VALUE)
    public void createRepairOrder(@RequestBody final RepairOrderDto repairOrderDto) {
        repairOrderService.createRepairOrder(repairOrderMapper.mapToRepairOrder(repairOrderDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRepairOrders")
    public List<RepairOrderDto> getRepairOrders() {
        return repairOrderMapper.mapToRepairOrderDtoList(repairOrderService.getRepairOrders());
    }
}
