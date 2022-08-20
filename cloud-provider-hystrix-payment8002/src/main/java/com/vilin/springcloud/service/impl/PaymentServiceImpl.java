package com.vilin.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vilin.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

  public String paymentInfo_OK(Integer id) {
    return "Thread Pool : " + Thread.currentThread().getName() + " PaymentInfo_OK id : " + id;
  }

  @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="5000")
  })
  public String paymentInfo_Timeout(Integer id) {
    int timeNum = 3;
//    int num = 15/0;
    try {
      TimeUnit.SECONDS.sleep(timeNum);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return "Thread Pool : " + Thread.currentThread().getName() + " PaymentInfo_Timeout id : " + id + " elapsed-time : " + timeNum + " seconds";

//    return "Thread Pool : " + Thread.currentThread().getName() + " PaymentInfo_Timeout id : " + id;
  }

  public String paymentInfoTimeoutHandler(Integer id){
    return "Thread Pool : " + Thread.currentThread().getName() + "  paymentInfoTimeOutHandler8002 server is busy or runtime exception，please try it later,id:  " + id;
  }

  @HystrixCommand(
          fallbackMethod = "paymentCircuitBreakerFallback",
          commandProperties = {
                  @HystrixProperty(
                          name = "circuitBreaker.enabled",
                          value = "true"), /* enable circuit breaker */
                  @HystrixProperty(
                          name = "circuitBreaker.requestVolumeThreshold",
                          value = "10"), // request number
                  @HystrixProperty(
                          name = "circuitBreaker.sleepWindowInMilliseconds",
                          value = "10000"), // time period
                  @HystrixProperty(
                          name = "circuitBreaker.errorThresholdPercentage",
                          value = "60") // failure rate
                  //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                  //value = "5000")// timeout
          })
  public String paymentCircuitBreaker(Integer id) {
    if (id < 0) {
      throw new RuntimeException("******id can not be negative number.");
    }
    String serialNumber = UUID.randomUUID().toString();
    return Thread.currentThread().getName() + "\t" + "access successfully，serial number: " + serialNumber;
  }

  public String paymentCircuitBreakerFallback(Integer id){
    return "the id can not be negative number: " +  id;
  }
}
