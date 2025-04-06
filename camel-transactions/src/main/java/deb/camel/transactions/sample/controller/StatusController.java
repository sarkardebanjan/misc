package deb.camel.transactions.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("Status Endpoint Reached", HttpStatus.OK);
    }
}