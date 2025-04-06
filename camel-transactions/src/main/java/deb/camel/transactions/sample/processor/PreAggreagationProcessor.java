package deb.camel.transactions.sample.processor;

import deb.camel.transactions.sample.model.Order;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class PreAggreagationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Order order = exchange.getIn().getBody(Order.class);
        Long orderId = order.getOrderId();
        exchange.getIn().setHeader("orderId", orderId);
        //Runtime.getRuntime().halt(1);
    }
}