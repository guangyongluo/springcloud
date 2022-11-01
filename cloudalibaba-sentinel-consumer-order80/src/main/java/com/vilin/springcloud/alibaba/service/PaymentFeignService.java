package com.vilin.springcloud.alibaba.service;

import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service", fallback = PaymentFeignFallbackService.class)
public interface PaymentFeignService {

    @GetMapping(value = "/payment/findPaymentById/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

}
