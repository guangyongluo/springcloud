package com.vilin.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelProviderPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelProviderPaymentMain8002.class, args);
    }
}
