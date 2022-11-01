package com.vilin.springcloud.alibaba.service.impl;

import com.vilin.springcloud.alibaba.dao.PaymentDao;
import com.vilin.springcloud.alibaba.service.PaymentService;
import com.vilin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentDao paymentDao;

  public Payment findPaymentById(@Param("id") Long id) {
    return paymentDao.findPaymentById(id);
  }

}
