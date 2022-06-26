package me.tests.camel.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class PostSplitProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Properties: " + exchange.getProperties());
        System.out.println("Headers: " + exchange.getIn().getHeaders());
        System.out.println("Body: " + exchange.getIn().getBody());
    }
}
