package br.com.marlonmoro.asyncsample;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class AsyncSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSampleApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(3);
		executor.setThreadNamePrefix("Async Thread Executor - ");
		executor.setQueueCapacity(500);
		executor.initialize();
		return executor;
	}


}
