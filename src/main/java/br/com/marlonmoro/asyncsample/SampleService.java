package br.com.marlonmoro.asyncsample;

import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  private final SomeActionService actionService;

  public Response syncExecute(){

    System.out.println("-------------- Sync Execution Start --------------");

    Instant start = Instant.now();

    actionService.firstAction();

    actionService.secondAction();

    actionService.thirdAction();

    Instant stop = Instant.now();
    System.out.println("-------------- Sync Execution Stop --------------");
    System.out.printf("Elapsed time ms %d \n", Duration.between(start, stop).toMillis());

    return new Response(Duration.between(start, stop).toMillis());
  }

  public SampleService(SomeActionService actionService) {
    this.actionService = actionService;
  }

  public Response asyncExecute(){

    System.out.println("-------------- Async Execution Start --------------");
    Instant start = Instant.now();

    actionService.firstAsyncAction();

    actionService.secondAsyncAction();

    actionService.thirdAsyncAction();

    Instant stop = Instant.now();
    System.out.println("-------------- Async Execution Stop --------------");
    System.out.printf("Elapsed time ms %d \n", Duration.between(start, stop).toMillis());

    return new Response(Duration.between(start, stop).toMillis());
  }



}
