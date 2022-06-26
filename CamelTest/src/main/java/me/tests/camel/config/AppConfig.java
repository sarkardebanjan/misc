package me.tests.camel.config;

import me.tests.camel.component.PostSplitProcessor;
import me.tests.camel.model.Trade;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    CamelContext camelContext;

    @Autowired
    PostSplitProcessor postSplitProcessor;

//    @Bean
//    RoutesBuilder fileCopyRoute1() {
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("file://C://GIT//input1").to("file://C://GIT//output1");
//            }
//        };
//    }
//
//    @Bean
//    RoutesBuilder produceToSedaRoute1() {
//        return new RouteBuilder() {
//          @Override
//          public void configure() throws Exception {
//              from("timer://simpleTimer?period=1000").process(new Processor() {
//                          @Override
//                          public void process(Exchange exchange) throws Exception {
//                            String body = "Hello from timer at " + new Date();
//                            exchange.getIn().setBody(body);
//                          }
//                      })
//                      //.setBody(simple("Hello from timer at " + new Date()))
//                      .to("seda:out1");
//
//              from("seda:out1").process(new Processor() {
//                  @Override
//                  public void process(Exchange exchange) throws Exception {
//                      System.out.println("Message read from seda: " + (exchange.getIn().getBody()).toString());
//                  }
//              });
//          }
//        };
//    }

    @Bean
    RoutesBuilder directToSplitter() {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("timer://simpleSplitTimer?period=1000000").process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Trade trade1 = new Trade(1L, 15.4, 200, "WF");
                                Trade trade2 = new Trade(2L, 75.1, 100, "GS");
                                Trade trade3 = new Trade(3L, 20.0, 15, "JP");
                                List<Trade> tradeList = new ArrayList<>();
                                tradeList.add(trade1);
                                tradeList.add(trade2);
                                tradeList.add(trade3);
                                exchange.getIn().setHeader("GUITrade", "Y");
                                exchange.getIn().setBody(tradeList);
                            }
                        })
                        .to("direct:toSplit");

                from("direct:toSplit").split(body()).to("direct:afterSplit1");
                from("direct:afterSplit1").to("direct:afterSplit2");
                from("direct:afterSplit2").process(postSplitProcessor);
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
