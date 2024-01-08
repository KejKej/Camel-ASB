package com.messaging.poc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camel")
@RequiredArgsConstructor
@Slf4j
public class CamelController {

    private final ProducerTemplate producerTemplate;

    @PostMapping("")
    public String sendToTopic2() {
        String message = "Sending test message to topic-2";

        producerTemplate.sendBody("azure-servicebus:topic-2",message);

        return "Message sent to topic-2: " + message;
    }
}
