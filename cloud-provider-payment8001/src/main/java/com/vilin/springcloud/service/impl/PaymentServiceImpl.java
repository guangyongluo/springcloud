package com.vilin.springcloud.service.impl;

import com.vilin.springcloud.dao.PaymentDao;
import com.vilin.springcloud.entities.Payment;
import com.vilin.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentDao paymentDao;

  public int savePayment(Payment payment) {
    return paymentDao.savePayment(payment);
  }

  public Payment findPaymentById(@Param("id") Long id) {
    return paymentDao.findPaymentById(id);
  }
}
