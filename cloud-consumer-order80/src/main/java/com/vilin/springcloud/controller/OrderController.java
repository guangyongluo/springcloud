package com.vilin.springcloud.controller;

import com.vilin.springcloud.contants.UrlConstants;
import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

  @Autowired private UrlConstants urlConstants;
  @Autowired private RestTemplate restTemplate;

  @PostMapping("/consumer/create/payment")
  public CommonResult createPayment(@RequestBody Payment payment) {
    return restTemplate.postForObject(
        new StringBuilder(urlConstants.getBasic()).append(urlConstants.getCreate()).toString(),
        payment,
        CommonResult.class);
  }

  @GetMapping("/consumer/find/payment/{id}")
  public CommonResult findPayment(@PathVariable Long id) {
    return restTemplate.getForObject(
        new StringBuilder(urlConstants.getBasic())
            .append(urlConstants.getFind())
            .append(id)
            .toString(),
        CommonResult.class);
  }
}
