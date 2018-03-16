package com.czajor.carserviceportal.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main() {
        return "Welcome to CarServicePortal API!";
    }
}
