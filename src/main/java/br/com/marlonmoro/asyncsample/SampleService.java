package br.com.marlonmoro.asyncsample;

import br.com.marlonmoro.asyncsample.dto.Customer;
import br.com.marlonmoro.asyncsample.dto.Payment;
import br.com.marlonmoro.asyncsample.dto.Sample;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

  private CustomerService customerService;
  private PaymentService paymentService;

  /**
   *  Serviço executa duas ações para
   *  conseguir construir o objeto Sample
   *
   */
  public Sample createSyncSample() {

    try {
      System.out.println("------ Execução Síncrona ------");
      LocalDateTime startTime = LocalDateTime.now();

      Customer customer = customerService.createSyncCustomer();

      Payment payment = paymentService.processSyncPayment();

      LocalDateTime endTime = LocalDateTime.now();

      return new Sample(customer, payment,
          startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          Duration.between(startTime, endTime).toMillis());
    } catch (Exception ex) {
      log.error("Houve um erro ao tentar gerar um novo Sample {}", ex.getMessage());
      throw new RuntimeException(
          "Serviço temporariamente indisponível, tente novamente mais tarde");
    }

  }

  /**
   * Serviço executa as mesmas duas ações para conseguir construir o objeto Sample
   * porém de forma asincrona e paralela
   */
  public Sample createAsyncSample() {

    System.out.println("------ Execução Assíncrona ------");
    LocalDateTime startTime = LocalDateTime.now();
    CompletableFuture<Customer> asyncCustomer = customerService.createAsyncCustomer();
    CompletableFuture<Payment> paymentCompletableFuture = paymentService.processAsyncPayment();
    CompletableFuture<Void> allCompletable = CompletableFuture
        .allOf(asyncCustomer, paymentCompletableFuture);

    try {
      allCompletable.get();
      LocalDateTime endTime = LocalDateTime.now();
      return new Sample(asyncCustomer.get(), paymentCompletableFuture.get(),
          startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          Duration.between(startTime, endTime).toMillis());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Houve um erro ao tentar gerar um novo Sample {}", e.getMessage());
      throw new RuntimeException(
          "Serviço temporariamente indisponível, tente novamente mais tarde");
    } catch (ExecutionException e) {
      log.error("Houve um erro ao tentar gerar um novo Sample {}", e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }


  public Sample createAsyncExceptionSample() {

    System.out.println("------ Execução com erro Assíncrona ------");
    LocalDateTime startTime = LocalDateTime.now();
    CompletableFuture<Customer> asyncCustomer = customerService.createAsyncExceptionCustomer();
    CompletableFuture<Payment> paymentCompletableFuture = paymentService.processAsyncPayment();
    CompletableFuture<Void> allCompletable = CompletableFuture
        .allOf(asyncCustomer, paymentCompletableFuture);

    try {
      allCompletable.get();
      LocalDateTime endTime = LocalDateTime.now();
      return new Sample(asyncCustomer.get(), paymentCompletableFuture.get(),
          startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
          Duration.between(startTime, endTime).toMillis());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      paymentCompletableFuture.thenAccept(paymentService::cancelPayment);
      log.error("Houve um erro sistêmico gerar um novo Sample {}", e.getMessage());
      throw new RuntimeException(
          "Serviço temporariamente indisponível, tente novamente mais tarde");
    } catch (ExecutionException e) {
      paymentCompletableFuture.thenAccept(paymentService::cancelPayment);
      log.error("Houve um erro ao tentar gerar um novo Sample {}", e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  public SampleService(CustomerService customerService,
      PaymentService paymentService) {
    this.customerService = customerService;
    this.paymentService = paymentService;
  }

}
