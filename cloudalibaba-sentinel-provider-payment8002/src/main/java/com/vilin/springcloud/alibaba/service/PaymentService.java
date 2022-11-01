package com.vilin.springcloud.alibaba.service;

import com.vilin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

  public Payment findPaymentById(@Param("id") Long id);
}
