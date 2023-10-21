package org.trials.kafka.kafkasample.route;


import jakarta.annotation.PostConstruct;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.trials.kafka.kafkasample.model.Trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class KafkaRoutes {

    private String producerDestination;

    private String consumerDestination;

    @Produce
    private ProducerTemplate producerTemplate;

    //@Value("${kafka.brokers:localhost:9092}")
    //private String kafkaBrokers;

    @PostConstruct
    public void init() {
        //This needs to be uncommented and used if camel.component.kafka.brokers=localhost:9092 is not set in application.properties
        //producerDestination = new StringBuilder().append("kafka:KafkaSample-Topic1?brokers=").append(kafkaBrokers).append("&valueSerializer=org.trials.kafka.kafkasample.util.GenericKafkaSerializer").toString();
        producerDestination = new StringBuilder().append("kafkaInternal:KafkaSample-Topic1").append("?valueSerializer=org.trials.kafka.kafkasample.util.GenericKafkaSerializer").toString();
        consumerDestination = new StringBuilder().append("kafkaInternal:KafkaSample-Topic1").append("?valueDeserializer=org.trials.kafka.kafkasample.util.GenericKafkaDeserializer").toString();
    }

    @Bean
    RoutesBuilder directToKafka() {

        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {

                from("timer://produceToKafka?period=1000000").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Trade trade1 = new Trade(1L, new BigDecimal(5.4), new BigDecimal(200), "WF");
                        Trade trade2 = new Trade(2L, new BigDecimal(75.1), new BigDecimal(100), "GS");
                        List<Trade> tradeList = new ArrayList<>();
                        tradeList.add(trade1);
                        tradeList.add(trade2);
                        exchange.getIn().setHeader("GUITrade", "Y");
                        exchange.getIn().setBody(tradeList);
                        //producerTemplate.send(producerDestination, exchange);
                    }
                }).to(producerDestination);

                from(consumerDestination).process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<Trade> tradeList = exchange.getIn().getBody(List.class);
                        String isGUITrade = exchange.getIn().getHeader("GUITrade", String.class);
                        System.out.println("GUITrade Header Value: " + isGUITrade);
                        if (!CollectionUtils.isEmpty(tradeList)) {
                            tradeList.forEach(trade -> System.out.println(trade));
                        } else {
                            System.out.println("No data in body");
                        }
                    }
                });
            }
        };
    }

}

