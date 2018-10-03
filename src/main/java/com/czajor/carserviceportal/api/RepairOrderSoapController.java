package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.RepairOrderSoapDto;
import com.czajor.carserviceportal.mapper.RepairOrderSoapDtoMapper;
import com.czajor.carserviceportal.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Component
@WebService(serviceName="repair-orders")
public class RepairOrderSoapController {
    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private RepairOrderSoapDtoMapper repairOrderMapper;

    @WebMethod
    public List<RepairOrderSoapDto> getRepairOrders() {
        return repairOrderMapper.mapToRepairOrderSoapDtoList(repairOrderService.getRepairOrders());
    }

    @WebMethod
    public RepairOrderSoapDto getRepairOrderById(int id) {
        return repairOrderMapper.mapToRepairOrderSoapDto(repairOrderService.getRepairOrderById(id));
    }
}
