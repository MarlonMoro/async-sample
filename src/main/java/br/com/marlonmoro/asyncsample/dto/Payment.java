package br.com.marlonmoro.asyncsample.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Payment {

  private String authorizationCode;
  private String uuid;
  private BigDecimal Value;

  public Payment(String authorizationCode, BigDecimal value) {
    this.authorizationCode = authorizationCode;
    this.uuid = UUID.randomUUID().toString();
    Value = value;
  }
}
