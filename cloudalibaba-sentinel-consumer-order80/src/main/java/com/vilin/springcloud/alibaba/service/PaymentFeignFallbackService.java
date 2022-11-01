package com.vilin.springcloud.alibaba.service;

import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallbackService implements PaymentFeignService{
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(400, "can not find payment order for id : " + id);
    }
}
