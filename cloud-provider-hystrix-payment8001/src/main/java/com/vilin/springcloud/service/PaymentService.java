package com.vilin.springcloud.service;

public interface PaymentService {

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_Timeout(Integer id);

    public String paymentCircuitBreaker(Integer id);

}
