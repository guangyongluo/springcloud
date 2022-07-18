package com.vilin.springcloud.dao;

import com.vilin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

  public int savePayment(Payment payment);

  public Payment findPaymentById(@Param("id") Long id);
}
