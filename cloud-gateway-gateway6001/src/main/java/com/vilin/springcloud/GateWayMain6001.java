package com.vilin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain6001 {

  public static void main(String[] args) {
      SpringApplication.run(GateWayMain6001.class, args);
  }
}
