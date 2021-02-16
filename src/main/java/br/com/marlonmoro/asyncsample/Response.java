package br.com.marlonmoro.asyncsample;

import lombok.Getter;

@Getter
public class Response {

  private final long duration;

  public Response(long duration) {
    this.duration = duration;
  }
}
