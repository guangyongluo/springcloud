package com.vilin.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vilin.springcloud.alibaba.service.PaymentFeignService;
import com.vilin.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenFeignOrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/openfeign/find/payment/{id}")
    @SentinelResource(value = "findPaymentById", blockHandler = "blockHandler")
    public CommonResult findPayment(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    public CommonResult blockHandler(String id, BlockException e){
        return new CommonResult(404, e.getClass() + ": current limit by sentinel.");
    }

}
