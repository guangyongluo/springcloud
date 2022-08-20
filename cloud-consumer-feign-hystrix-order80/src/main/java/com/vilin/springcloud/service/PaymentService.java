package com.vilin.springcloud.service;

import com.vilin.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentServiceImpl.class)
public interface PaymentService {

  @GetMapping("/payment/hystrix/ok/{id}")
  public String paymentInfo_OK(@PathVariable("id") Integer id);

  @GetMapping("/payment/hystrix/timeout/{id}")
  public String paymentInfo_Timeout(@PathVariable("id") Integer id);

  @GetMapping("/payment/circuit/{id}")
  public String paymentInfo_CircuitBreaker(@PathVariable("id") Integer id);

}
