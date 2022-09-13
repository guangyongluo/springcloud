package com.vilin.springcloud.controller;

import com.vilin.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class sendMessageController {

    @Autowired
    private IMessageProvider provider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        String message = UUID.randomUUID().toString();
        return provider.sendMessage(message);
    }
}
