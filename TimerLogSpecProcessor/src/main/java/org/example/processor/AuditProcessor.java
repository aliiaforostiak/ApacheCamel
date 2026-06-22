package org.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class AuditProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getMessage().getBody(String.class);
        System.out.println("Processed: " + body);
        exchange.getMessage().getHeaders().put("processed", "true");
    }
}
