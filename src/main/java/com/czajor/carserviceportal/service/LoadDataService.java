package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.exception.CarHasOwnerException;
import com.czajor.carserviceportal.exception.CarNotFoundException;
import com.czajor.carserviceportal.model.Car;
import com.czajor.carserviceportal.model.Customer;
import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.RepairOrderType;
import com.czajor.carserviceportal.repository.CarRepository;
import com.czajor.carserviceportal.repository.CustomerRepository;
import com.czajor.carserviceportal.repository.RepairOrderRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class LoadDataService {
    @Value("${customers.db.file.path}")
    private String CUSTOMERS_DB_FILE_PATH;
    @Value("${repairorders.db.file.path}")
    private String REPAIRORDERS_DB_FILE_PATH;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataService.class);
    private ResourceLoader resourceLoader;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RepairOrderRepository repairOrderRepository;

    public LoadDataService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void fromDbFile() {
        try {
            LOGGER.info("ReadDataService: Trying to load data from file...");

            List<Customer> customers = Arrays.asList(
                    transferDataToJson(Customer[].class, CUSTOMERS_DB_FILE_PATH));
            List<RepairOrder> repairOrders = Arrays.asList(
                    transferDataToJson(RepairOrder[].class, REPAIRORDERS_DB_FILE_PATH));
            repairOrders.forEach(System.out::print);
            LOGGER.info("Adding Customers to Cars");
            prepareCustomersData(customers).forEach(customerRepository::save);
            LOGGER.info("Adding Cars to Repair Order");
            prepareRepairOrders(repairOrders).forEach(repairOrderRepository::save);
            LOGGER.info("ReadDataService: Reading data succeeded!");
        } catch (IOException | NullPointerException e) {
            LOGGER.error("Cannot read DB data from file: ", e);
        }
    }

    private List<RepairOrder> prepareRepairOrders(List<RepairOrder> repairOrders) {
        try {
            for(RepairOrder repairOrder : repairOrders) {
                Car car = carRepository.findById(repairOrder.getCar().getId()).orElseThrow(CarNotFoundException::new);
                Set<RepairOrderType> repairOrderTypes = repairOrder.getRepairOrderType();
                repairOrder.setCar(car);
                repairOrder.getRepairOrderType().addAll(repairOrderTypes);
            }
        } catch (CarNotFoundException e) {
            LOGGER.error("Cannot add car to RepairOrder: " + e);
        }
        return repairOrders;
    }

    private List<Customer> prepareCustomersData(List<Customer> customers) {
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

    private <T> T transferDataToJson(Class<T> objectType, String filePath) throws IOException {
        Gson jsonWithData = new Gson();
        return jsonWithData.fromJson(
                getBufferedDataFromFile(filePath), objectType);
    }

    private BufferedReader getBufferedDataFromFile(String dbFilePath) throws IOException, NullPointerException {
        Resource resource = resourceLoader.getResource(dbFilePath);
        File fileWithData = resource.getFile();
        FileReader fileReader = new FileReader(fileWithData);
        return new BufferedReader(fileReader);
    }
}
