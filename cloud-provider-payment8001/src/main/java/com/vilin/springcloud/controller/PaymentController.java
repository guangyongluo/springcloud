package com.vilin.springcloud.controller;

import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import com.vilin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

  @Autowired private PaymentService paymentService;

  @PostMapping(value = "/payment/create")
  public CommonResult createPayment(@RequestBody Payment payment) {
    int result = paymentService.savePayment(payment);
    log.info("========insert payment number : {}", result);

    if (result > 0) {
      return new CommonResult(200, "insert successfully.", result);
    } else {
      return new CommonResult(400, "insert failure.", null);
    }
  }

  @GetMapping(value = "/payment/findPaymentById/{id}")
  public CommonResult<Payment> findPaymentById(@PathVariable("id") Long id) {
    Payment result = paymentService.findPaymentById(id);
    log.info("========find payment: {}", result);

    if (result != null) {
      return new CommonResult(200, "insert successfully.", result);
    } else {
      return new CommonResult(400, "insert failure.", null);
    }
  }
}
