package com.vilin.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vilin.springcloud.alibaba.constants.UrlConstants;
import com.vilin.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

  @Autowired private UrlConstants urlConstants;
  @Autowired private RestTemplate restTemplate;

  @GetMapping("/consumer/find/payment/{id}")
  @SentinelResource(value = "findPaymentById", fallback = "exceptionHandler", blockHandler = "blockHandler")
  public CommonResult findPayment(@PathVariable String id) {

    if(NumberUtils.isDigits(id)){
      return restTemplate.getForObject(
              new StringBuilder(urlConstants.getBasic())
                      .append(urlConstants.getFind())
                      .append(id)
                      .toString(),
              CommonResult.class);
    }else{
      throw new IllegalArgumentException("the input id is not a number.");
    }

  }

  public CommonResult exceptionHandler(String id, Throwable e){
    return new CommonResult(400, e.getClass() + ": the input id is not a number");
  }

  public CommonResult blockHandler(String id, BlockException e){
    return new CommonResult(404, e.getClass() + ": current limit by sentinel.");
  }

}
