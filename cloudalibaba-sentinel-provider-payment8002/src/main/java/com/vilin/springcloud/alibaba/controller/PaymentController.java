package com.vilin.springcloud.alibaba.controller;

import com.vilin.springcloud.alibaba.service.PaymentService;
import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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
}
