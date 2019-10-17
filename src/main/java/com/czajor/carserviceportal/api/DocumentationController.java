package com.czajor.carserviceportal.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/documentation")
public class DocumentationController {

    @GetMapping(value = "/api")
    public String greeting() {
        return "redirect:/swagger-ui.html";
    }
}
