package com.vilin.springcloud.service;

import com.vilin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

  public int savePayment(Payment payment);

  public Payment findPaymentById(@Param("id") Long id);
}
