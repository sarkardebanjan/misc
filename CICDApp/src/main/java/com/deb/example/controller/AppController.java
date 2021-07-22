package com.deb.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/cicdData")
    public String getData() {
        return "Rest response from cicdData";
    }

}
