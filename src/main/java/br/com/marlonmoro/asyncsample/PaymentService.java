package br.com.marlonmoro.asyncsample;

import br.com.marlonmoro.asyncsample.dto.Payment;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

  public long waitTime = 1000L;

  @Async
  public CompletableFuture<Payment> processAsyncPayment(){
    try {
      Thread.sleep(waitTime);
      return CompletableFuture.completedFuture(new Payment("authorizationCode", BigDecimal.valueOf(100L)));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Não foi possível realizar o pagamento");
    }
  }

  public Payment processSyncPayment(){
    try {
      Thread.sleep(waitTime);
      return new Payment("authorizationCode", BigDecimal.valueOf(100L));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Não foi possível realizar o pagamento");
    }
  }

  @Async
  public void cancelPayment(Payment payment) {
    log.info("Cancelando pagamento {}", payment.toString());
    try {
      Thread.sleep(waitTime);
      log.info("Pagamento cancelado com sucesso {}", payment.toString());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Erro ao cancelar pagamento {}, {}", e.getMessage(), payment.toString());
    }
  }

}
