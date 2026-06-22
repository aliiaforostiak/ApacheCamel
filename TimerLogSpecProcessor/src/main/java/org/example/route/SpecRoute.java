package org.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.example.processor.AuditProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpecRoute extends RouteBuilder {

    private final AuditProcessor auditProcessor;

    public SpecRoute(AuditProcessor auditProcessor) {
        this.auditProcessor = auditProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("timer:test?period=3000")
                .setBody(constant("Hello Camel"))
                .setHeader("source", constant("timer"))
                .process(auditProcessor)
                .process(exchange -> System.out.println("Processed header value: " + exchange.getMessage().getHeader("processed")))
                .to("log:info");
    }
}
