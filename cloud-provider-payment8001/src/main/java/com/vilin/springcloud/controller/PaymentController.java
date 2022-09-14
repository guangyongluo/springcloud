package com.vilin.springcloud.controller;

import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import com.vilin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

  @Autowired private PaymentService paymentService;

  @Value("${server.port}")
  private String serverPort;

  @PostMapping(value = "/payment/create")
  public CommonResult createPayment(@RequestBody Payment payment) {
    int result = paymentService.savePayment(payment);
    log.info("========insert payment number : " + serverPort, result);

    if (result > 0) {
      return new CommonResult(200, "insert successfully. {} server port : " + serverPort, result);
    } else {
      return new CommonResult(400, "insert failure. {} server port : " + serverPort, null);
    }
  }

  @GetMapping(value = "/payment/findPaymentById/{id}")
  public CommonResult<Payment> findPaymentById(@PathVariable("id") Long id) {
    Payment result = paymentService.findPaymentById(id);
    log.info("========find payment: {} server port : " + serverPort, result);

    if (result != null) {
      return new CommonResult(200, "find successfully. {} server port : " + serverPort, result);
    } else {
      return new CommonResult(400, "find failure. {} server port : " + serverPort, null);
    }
  }

  @GetMapping("/payment/lb")
  public String getPaymentLB() {
    return serverPort;
  }

  @GetMapping("/payment/feign/timeout")
  public String getFeignTimeOut() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return serverPort;
  }

  @GetMapping("/payment/zipkin")
  public String paymentZipkin(){
    return "access zipkin server form payment8001";
  }
}
