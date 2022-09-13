package com.vilin.springcloud.service.impl;

import com.vilin.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@Slf4j
@EnableBinding(Source.class)
public class MessageProvider implements IMessageProvider {

    @Autowired
    private MessageChannel output;

    @Override
    public String sendMessage(String message) {
        output.send(MessageBuilder.withPayload(message).build());
        log.info("************ message : " + message);
        return message;
    }
}
