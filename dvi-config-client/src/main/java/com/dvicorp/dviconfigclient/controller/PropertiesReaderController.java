package com.dvicorp.dviconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesReaderController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Value("${can.read}")
    private boolean canRead;

    @GetMapping(value = "/welcome")
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @GetMapping(value = "/canRead")
    public boolean getCanRead() {
        return canRead;
    }
}
