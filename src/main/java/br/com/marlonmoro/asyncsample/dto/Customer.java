package br.com.marlonmoro.asyncsample.dto;

import lombok.Getter;

@Getter
public class Customer {

  private String name;
  private String cpf;
  private Integer age;

  public Customer(String name, String cpf, Integer age) {
    this.name = name;
    this.cpf = cpf;
    this.age = age;
  }
}
