package com.vilin.sprintcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
public class HystrixTurbineMain9002 {
  public static void main(String[] args) {
      SpringApplication.run(HystrixTurbineMain9002.class, args);
  }
}
