package com.vilin.springcloud.controller;

import com.vilin.springcloud.contants.UrlConstants;
import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import com.vilin.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

  @Autowired private UrlConstants urlConstants;
  @Autowired private RestTemplate restTemplate;

  @Autowired private LoadBalance loadBalance;

  @Autowired
  private DiscoveryClient discoveryClient;

  @PostMapping("/consumer/create/payment")
  public CommonResult createPayment(@RequestBody Payment payment) {
    return restTemplate.postForObject(
        new StringBuilder(urlConstants.getBasic()).append(urlConstants.getCreate()).toString(),
        payment,
        CommonResult.class);
  }

  @PostMapping("/consumer/getForEntity/payment")
  public CommonResult insertPayment(@RequestBody Payment payment) {
    ResponseEntity<CommonResult> entity =
        restTemplate.postForEntity(
            new StringBuilder(urlConstants.getBasic()).append(urlConstants.getCreate()).toString(),
            payment,
            CommonResult.class);
    if (entity.getStatusCode().is2xxSuccessful()) {
      log.info("response code : " + entity.getStatusCode());
      return entity.getBody();
    } else {
      return new CommonResult(400, "insert payment failed!");
    }
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

  @GetMapping("/consumer/getForEntity/payment/{id}")
  public CommonResult getPayment(@PathVariable Long id) {
    ResponseEntity<CommonResult> entity =
        restTemplate.getForEntity(
            new StringBuilder(urlConstants.getBasic())
                .append(urlConstants.getFind())
                .append("{1}")
                .toString(),
            CommonResult.class,
            id);
    if (entity.getStatusCode().is2xxSuccessful()) {
      log.info("response code : " + entity.getStatusCode());
      return entity.getBody();
    } else {
      return new CommonResult(400, "get payment failed!");
    }
  }

  @GetMapping("/consumer/payment/lb")
  public String getPaymentLB() {
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    if(instances == null || instances.size() <= 0){
      return null;
    }
    ServiceInstance serviceInstance = loadBalance.instances(instances);
    final URI uri = serviceInstance.getUri();

    return restTemplate.getForObject(uri + "/payment/lb", String.class);
  }
}
