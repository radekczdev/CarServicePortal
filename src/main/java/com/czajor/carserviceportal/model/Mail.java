package com.czajor.carserviceportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String body;
}
