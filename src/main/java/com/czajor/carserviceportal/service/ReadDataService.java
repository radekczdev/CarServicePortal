package com.czajor.carserviceportal.service;

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
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class ReadDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadDataService.class);
    private ResourceLoader resourceLoader;

    @Value("${db.file.path}")
    private String DB_FILE_PATH;

    @Autowired
    public ReadDataService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    public CustomerService customerService;

    @PostConstruct
    public void loadData() {
        try {
            LOGGER.info("ReadDataService: Trying to load data from file...");

            Resource resource = resourceLoader.getResource(DB_FILE_PATH);
            File fileWithData = resource.getFile();
            FileReader fileReader = new FileReader(fileWithData);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Gson jsonWithData = new Gson();
            List<Customer> customers = Arrays.asList(jsonWithData.fromJson(bufferedReader, Customer[].class));

//            customers.forEach(customerService::addCustomer);  -- should be checked

            LOGGER.info("ReadDataService: Reading data succeeded!");

        } catch (IOException | NullPointerException e) {
            LOGGER.error("Cannot read DB data from file: ", e);
        }
    }
}
