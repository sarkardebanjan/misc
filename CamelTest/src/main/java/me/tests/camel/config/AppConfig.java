package me.tests.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ThreadPoolProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class AppConfig {

    @Autowired
    CamelContext camelContext;

    @Bean
    RoutesBuilder fileCopyRoute1() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://C://GIT//input1").to("file://C://GIT//output1");
            }
        };
    }

    @Bean
    RoutesBuilder produceToSedaRoute1() {
        return new RouteBuilder() {
          @Override
          public void configure() throws Exception {
              from("timer://simpleTimer?period=1000").process(new Processor() {
                          @Override
                          public void process(Exchange exchange) throws Exception {
                            String body = "Hello from timer at " + new Date();
                            exchange.getIn().setBody(body);
                          }
                      })
                      //.setBody(simple("Hello from timer at " + new Date()))
                      .to("seda:out1");

              from("seda:out1").process(new Processor() {
                  @Override
                  public void process(Exchange exchange) throws Exception {
                      System.out.println("Message read from seda: " + (exchange.getIn().getBody()).toString());
                  }
              });
          }
        };
    }

    @PostConstruct
    public void init() {
        ThreadPoolProfile threadPoolProfile = camelContext.getExecutorServiceManager().getDefaultThreadPoolProfile();
        System.out.println("Thread Pool Size: " + threadPoolProfile.getPoolSize());
        System.out.println("Max Thread Pool Size: " + threadPoolProfile.getMaxPoolSize());
        System.out.println("Max Queue Size: " + threadPoolProfile.getMaxQueueSize());
    }
}
