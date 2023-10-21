package org.example.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransactedJmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        /*
        Non transacted route

        from("activemq:foo")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println(body);
                    }
                })
                .to("log:sample");

        from("timer:bar")
                .setBody(constant("Hello from Camel"))
                .to("activemq:foo");

        */

        from("activeMqTx:foo")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println("isExchangeTransacted: " + exchange.isTransacted());
                        System.out.println(body);
                    }
                })
                .to("log:sample");

        from("timer:bar")
                .setBody(constant("Hello from Camel"))
                .to("activeMqTx:foo");
    }

}
