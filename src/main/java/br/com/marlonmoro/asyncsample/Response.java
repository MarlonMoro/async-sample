package br.com.marlonmoro.asyncsample;

import lombok.Getter;

@Getter
public class Response {

  private final long runtime;

  public Response(long runtime) {
    this.runtime = runtime;
  }
}
