package com.czajor.carserviceportal;

import com.czajor.carserviceportal.domain.CarDto;
import com.czajor.carserviceportal.exception.CarNotFoundException;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.StatusType;
import com.czajor.carserviceportal.repository.CarRepository;
import com.czajor.carserviceportal.samples.RepairOrderGenerator;
import com.czajor.carserviceportal.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CarServicePortalTestSuite {

    @MockBean
    private CarRepository carRepository;

    @Test
    public void testCreateOrder() {
        // Given
        RepairOrderGenerator orderGenerator = new RepairOrderGenerator();
        RepairOrder repairOrder = orderGenerator.generateSampleOrder();

        System.out.println(repairOrder);

        // When
        repairOrder.changeStatus(StatusType.QUEUE);
        repairOrder.changeStatus(StatusType.READY);
        StatusType currentStatus = repairOrder.getCurrentStatus().getStatusType();

        System.out.println(repairOrder);

        // Then
        Assert.assertEquals(2, repairOrder.getPreviousStatusList().size());
        Assert.assertEquals(StatusType.READY, currentStatus);
    }

    @Test
    public void shouldNotModifyCar() {
        // Given
        CarService carService = new CarService(carRepository);
        CarDto carDto = mock(CarDto.class);
        when(carRepository.findById(any())).thenReturn(Optional.empty());
        // When

        //Then
        assertThrows(CarNotFoundException.class, () -> carService.modifyCar(carDto));
    }
}
