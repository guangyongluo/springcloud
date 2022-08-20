package com.vilin.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vilin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "paymentGlobalFallback")
@Slf4j
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/circuit/{id}")
    public String payment_CircuitBreaker(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_CircuitBreaker(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="1500")
//    })
    @HystrixCommand
    public String paymentFeignTimeout(@PathVariable("id") Integer id) {
        int num = 15/0;
        return paymentService.paymentInfo_Timeout(id);
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "Thread Pool : " + Thread.currentThread().getName() + "  paymentInfo server is busy or runtime exception，please try it later,id:  " + id;
    }

    public String paymentGlobalFallback(){
        return "Global fallback method!";
    }
}
