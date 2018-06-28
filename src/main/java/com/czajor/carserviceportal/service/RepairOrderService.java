package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.domain.StatusTypeDto;
import com.czajor.carserviceportal.exception.OrderNotFoundException;
import com.czajor.carserviceportal.files.HttpHeadersGenerator;
import com.czajor.carserviceportal.mapper.StatusTypeMapper;
import com.czajor.carserviceportal.model.Mail;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.StatusType;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public ResponseEntity<byte[]> generateReport(int reportId) {
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            RepairOrder repairOrder = repairOrderRepository.findById(reportId).orElseThrow(OrderNotFoundException::new);
            String header = "REPAIR ORDER no " + repairOrder.getId();
            String[][] content = {{"car license plates: ", repairOrder.getCar().getId()},
            		{"current order status", repairOrder.getCurrentStatus().toString()},
            		{"date of creation", repairOrder.getDateOfCreation().toString()}
            };
            output = createPdDocument(header, content);
        } catch (Exception e) {
            System.out.println("generateReport thrown: " + e + e.getMessage());
        }
        
        HttpHeaders headers = HttpHeadersGenerator.generate();
        return new ResponseEntity<>(output.toByteArray(), headers, HttpStatus.OK);
    }
    
    private ByteArrayOutputStream createPdDocument(String header, String[][] content) throws IOException {
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 30);
        contentStream.newLineAtOffset(150, 600);
        contentStream.showText(header);
        contentStream.endText();
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
        contentStream.newLineAtOffset(25, 350);
        for(String[] text : content) {
        	contentStream.showText(text[0] + ": " + text[1]);
        	contentStream.newLineAtOffset(0, -15);
        }           
        contentStream.endText();
        contentStream.close();
        document.save(output);
        document.close();
        return output;
    }
}
