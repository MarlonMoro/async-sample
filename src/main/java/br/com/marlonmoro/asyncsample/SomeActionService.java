package br.com.marlonmoro.asyncsample;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SomeActionService {

  public void firstAction(){
    try {
      Thread.sleep(200L);
      System.out.println("First Sync Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void secondAction(){
    try {
      Thread.sleep(200L);
      System.out.println("Second Sync Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void thirdAction(){
    try {
      Thread.sleep(200L);
      System.out.println("Third Sync Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Async
  public void firstAsyncAction(){
    try {
      Thread.sleep(200L);
      System.out.println("First Async Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Async
  public void secondAsyncAction(){
    try {
      Thread.sleep(200L);
      System.out.println("Second Async Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Async
  public void thirdAsyncAction(){
    try {
      Thread.sleep(200L);
      System.out.println("Third  Async Action Completed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
