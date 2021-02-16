package br.com.marlonmoro.asyncsample;

import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class SyncService {

  public Response execute(){

    Instant start = Instant.now();

    try {
      Thread.sleep(200L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    Instant stop = Instant.now();
    System.out.printf("Tempo decorrido em milisegundos %d", Duration.between(start, stop).toMillis());

    return new Response(Duration.between(start, stop).toMillis());
  }

}
