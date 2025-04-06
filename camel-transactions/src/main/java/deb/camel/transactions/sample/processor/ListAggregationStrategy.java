package deb.camel.transactions.sample.processor;

import deb.camel.transactions.sample.model.Order;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (null == oldExchange) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(newExchange.getIn().getBody(Order.class));
            newExchange.getIn().setBody(orderList);
            return newExchange;
        }

        Message oldMessage = oldExchange.getIn();
        List<Order> orderList = oldMessage.getBody(List.class);
        orderList.add(newExchange.getIn().getBody(Order.class));
        return oldExchange;
    }
}
