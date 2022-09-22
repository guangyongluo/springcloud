package com.vilin.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Value("${service-url.cloud-nacos-provider}")
    private String providerURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/get/payment/{id}")
    public String getPaymentInfo(@PathVariable("id") String id){
        return restTemplate.getForObject(providerURL + "/payment/nacos/" + id, String.class);
    }

}
