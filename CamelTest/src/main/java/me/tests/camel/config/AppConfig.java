package me.tests.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ThreadPoolProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    public void init() {
        ThreadPoolProfile threadPoolProfile = camelContext.getExecutorServiceManager().getDefaultThreadPoolProfile();
        System.out.println("Thread Pool Size: " + threadPoolProfile.getPoolSize());
        System.out.println("Max Thread Pool Size: " + threadPoolProfile.getMaxPoolSize());
        System.out.println("Max Queue Size: " + threadPoolProfile.getMaxQueueSize());
    }
}
