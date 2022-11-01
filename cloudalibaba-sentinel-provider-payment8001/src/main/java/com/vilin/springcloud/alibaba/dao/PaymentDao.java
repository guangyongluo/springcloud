package com.vilin.springcloud.alibaba.dao;

import com.vilin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

  public Payment findPaymentById(@Param("id") Long id);

}
