package com.vilin.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientMain7001 {
  public static void main(String[] args) {
      SpringApplication.run(ConfigClientMain7001.class, args);
  }
}
