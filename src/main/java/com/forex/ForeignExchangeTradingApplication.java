package com.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ForeignExchangeTradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeTradingApplication.class, args);
	}
}
