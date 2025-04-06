package deb.camel.transactions.sample.processor;

import deb.camel.transactions.sample.model.Order;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostAggregationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Order> orderList = exchange.getIn().getBody(List.class);
    }
}