package com.dvicorp.dviconfigclient.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Value("${can.read}")
    private boolean canRead;

    @PostConstruct
    public void init() {
        System.out.println("Data:");
        System.out.println("canRead:" + canRead);
        System.out.println("welcomeMessage:" + welcomeMessage);
    }

}
