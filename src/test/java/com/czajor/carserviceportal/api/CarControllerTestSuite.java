package com.czajor.carserviceportal.api;

import com.czajor.carserviceportal.domain.CarDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarController carController;

    @Test
    public void shouldReturnOneCar() throws Exception{
        // Given
        String licensePlate = "123";
        CarDto carDto = new CarDto(licensePlate, null, null, null, null, null);
        when(carController.getCar(anyString())).thenReturn(carDto);

        // When&Then
        mockMvc.perform(get("/cars/{carId}", licensePlate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.licensePlate", is(licensePlate)));
    }

    @Test
    public void shouldReturnCarList() {
    }

    @Test
    public void shouldChangeCarModelName() {
    }

    @Test
    public void shouldDeleteCar() {
    }
}