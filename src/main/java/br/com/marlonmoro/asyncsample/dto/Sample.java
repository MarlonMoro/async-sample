package br.com.marlonmoro.asyncsample.dto;

import lombok.Getter;

@Getter
public class Sample {

  private Customer customer;
  private Payment payment;
  private String startTime;
  private String endTime;
  private Long duration;

  public Sample() {
  }

  public Sample(Customer customer, Payment payment, String startTime, String endTime,
      Long duration) {
    this.customer = customer;
    this.payment = payment;
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = duration;
  }
}
