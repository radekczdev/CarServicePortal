package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.exception.CarHasOwnerException;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class LoadDataService {
    @Value("${db.file.path}")
    private String DB_FILE_PATH;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataService.class);
    private ResourceLoader resourceLoader;
    @Autowired
    private CustomerService customerService;

    public LoadDataService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void fromDbFile() {
        try {
            LOGGER.info("ReadDataService: Trying to load data from file...");

            Gson jsonWithData = new Gson();
            List<Customer> customers = Arrays.asList(
                    jsonWithData.fromJson(
                            getDataFromFile(DB_FILE_PATH), Customer[].class)
                );

            LOGGER.info("Adding Customers to Cars");
            prepareCustomerCars(customers).forEach(customerService::addCustomer);
            LOGGER.info("ReadDataService: Reading data succeeded!");

        } catch (IOException | NullPointerException e) {
            LOGGER.error("Cannot read DB data from file: ", e);
        }
    }

    private List<Customer> prepareCustomerCars(List<Customer> customers) {
        try {
            for(Customer customer : customers) {
                List<Car> carList = customer.getCarList();
                for (Car car : carList) {
                    car.addCustomer(customer);
                }
            }
        } catch (CarHasOwnerException e) {
            LOGGER.error("Cannot add customer to car instance: " + e);
        }
        return customers;
    }

    private BufferedReader getDataFromFile(String dbFilePath) throws IOException, NullPointerException {
        Resource resource = resourceLoader.getResource(dbFilePath);
        File fileWithData = resource.getFile();
        FileReader fileReader = new FileReader(fileWithData);
        return new BufferedReader(fileReader);
    }
}
