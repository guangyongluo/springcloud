package com.vilin.springcloud.contants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("payment.url")
public class UrlConstants {
  private String basic;
  private String create;
  private String find;
}
