package br.com.marlonmoro.asyncsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSampleApplication.class, args);
	}



}
