package com.messaging.poc;

import org.apache.camel.CamelContext;
import org.apache.camel.component.azure.servicebus.ServiceBusComponent;
import org.apache.camel.component.azure.servicebus.ServiceBusType;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Bean
    public CamelContext camelContext(CustomCamelRoute customCamelRoute) throws Exception{
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("azure-servicebus",serviceBusComponent());
        camelContext.addRoutes(customCamelRoute);

        camelContext.start();

        return camelContext;
    }

    @Bean
    public ServiceBusComponent serviceBusComponent() {
        ServiceBusComponent serviceBusComponent = new ServiceBusComponent();
        serviceBusComponent.getConfiguration().setConnectionString(connectionString);
        serviceBusComponent.getConfiguration().setServiceBusType(ServiceBusType.topic);
        return serviceBusComponent;
    }
}
