package com.vilin.springcloud.service.impl;

import com.vilin.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK fallback method in PaymentServiceImpl";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout fallback method in PaymentServiceImpl";
    }

    @Override
    public String paymentInfo_CircuitBreaker(Integer id) {
        return "paymentInfo_CircuitBreaker fallback method in PaymentServiceImpl";
    }
}
