package com.messaging.poc;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.azureServicebus;

@Component
public class CustomCamelRoute extends RouteBuilder{

    public void configure() {
        from(azureServicebus("topic-2")
                .subscriptionName("topic-2-subscription"))
                .log("Received message in topic-2: ${body}")
                .process(exchange -> {
                    String originalBody = exchange.getIn().getBody(String.class);
                    String transformedBody = originalBody + " with appended String";
                    exchange.getIn().setBody(transformedBody);
                })
                        .to(azureServicebus("topic-3"));

        from(azureServicebus("topic-3")
                .subscriptionName("topic-3-subscription"))
                .log("Received transformed message in topic-3: ${body}");

    }

}
