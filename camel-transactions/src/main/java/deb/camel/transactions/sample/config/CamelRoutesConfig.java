package deb.camel.transactions.sample.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import deb.camel.transactions.sample.processor.ListAggregationStrategy;
import deb.camel.transactions.sample.processor.PreAggreagationProcessor;
import deb.camel.transactions.sample.processor.PostAggregationProcessor;

@Configuration("camelRoutesConfig")
public class CamelRoutesConfig implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelRoutesConfig.class);

//    private CamelContext camel;

    private ApplicationContext applicationContext;

    @Autowired
    private PreAggreagationProcessor preAggreagationProcessor;

    @Autowired
    private ListAggregationStrategy listAggregationStrategy;

    @Autowired
    private PostAggregationProcessor postAggregationProcessor;

    /*
    @Bean(name = "camel")
    public CamelContext camel() throws Exception {
        camel = new SpringCamelContext(applicationContext);
        RouteBuilder consumeFromQueueRoute = createConsumeFRomQueueRoute();
        camel.addRoutes(consumeFromQueueRoute);
        camel.start();
        return camel;
    }

    private RouteBuilder createConsumeFRomQueueRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("solaceJmsComponent:queue:SOME_TEST_QUEUE")
                        .transacted("chainedTransactionPolicy")
                        .process(preAggreagationProcessor)
                        .idempotentConsumer(header("orderId"), MemoryIdempotentRepository.memoryIdempotentRepository(5000))
                        .aggregate(constant(true), listAggregationStrategy)
                        .completionPredicate(constant(true))
                        .process(postAggregationProcessor)
                        .end();
            }
        };
    }
    */

    @Bean
    public RouteBuilder routeBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("solaceJmsComponent:queue:SOME_TEST_QUEUE")
                        .transacted("chainedTransactionPolicy")
                        .process(preAggreagationProcessor)
                        .idempotentConsumer(header("orderId"), MemoryIdempotentRepository.memoryIdempotentRepository(5000))
                        .aggregate(constant(true), listAggregationStrategy)
                        .completionPredicate(constant(true))
                        .process(postAggregationProcessor)
                        .end();
            }
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}