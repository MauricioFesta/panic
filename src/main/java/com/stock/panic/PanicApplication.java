package com.stock.panic;

import org.springframework.boot.SpringApplication;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.services.LoginService;
import com.stock.panic.repository.LoginRepository;


@SpringBootApplication
public class PanicApplication {

	public static void main(String[] args) {

		SpringApplication.run(PanicApplication.class, args);
	}

}