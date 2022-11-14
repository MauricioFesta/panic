package com.stock.panic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PanicApplication {

	public static void main(String[] args) {

		SpringApplication.run(PanicApplication.class, args);
	}

}