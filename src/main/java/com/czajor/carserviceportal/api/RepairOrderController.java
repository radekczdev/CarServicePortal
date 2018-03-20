package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.RepairOrderDto;
import com.czajor.carserviceportal.domain.StatusTypeDto;
import com.czajor.carserviceportal.mapper.RepairOrderMapper;
import com.czajor.carserviceportal.service.RepairOrderService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
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

    @RequestMapping(method = RequestMethod.PUT, value = "changeOrderStatus")
    public void changeOrderStatus(@RequestBody final StatusTypeDto statusType, @RequestParam int repairOrderId) {
        repairOrderService.changeRepairOrderStatus(repairOrderId, statusType);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "getReport"
            )
    public ResponseEntity<byte[]> getReport(@RequestParam int id) {
        byte[] contents = repairOrderService.generateReport(id).toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
}
