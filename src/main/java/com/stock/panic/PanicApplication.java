package com.stock.panic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PanicApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(PanicApplication.class, args);
	}

}