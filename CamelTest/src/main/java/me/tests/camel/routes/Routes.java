package me.tests.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //from("file://C://GIT//input//").to("file://C://GIT//output//");
    }
}
