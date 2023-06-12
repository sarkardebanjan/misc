package com.dvicorp.mypassion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Hello! User.", HttpStatus.OK);
    }
}
