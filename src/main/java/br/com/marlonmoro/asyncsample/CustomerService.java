package br.com.marlonmoro.asyncsample;

import br.com.marlonmoro.asyncsample.dto.Customer;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  public long waitTime = 1000L;

  /**
   *  Simulação de execução de um cadastro
   *  demorado e assíncrono
   *
   */
  @Async
  public CompletableFuture<Customer> createAsyncCustomer() {
    try {
      Thread.sleep(waitTime);
      return CompletableFuture.completedFuture(new Customer("Nome Cliente", "00151756007", 45));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Não foi possível realizar o cadastro do cliente");
    }
  }

  /**
   *  Simulação de execução de um cadastro
   *  demorado e síncrono
   *
   */
  public Customer createSyncCustomer() {
    try {
      Thread.sleep(waitTime);
      return new Customer("Nome Cliente", "00151756007", 45);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Não foi possível realizar o cadastro do cliente");
    }
  }

  /**
   * Simula um erro ao cadastrar
   */
  @Async
  public CompletableFuture<Customer> createAsyncExceptionCustomer() {
    throw new RuntimeException("Erro ao tentar criar um Customer");
  }

}
