package com.stock.panic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.LoginApplication;

@SpringBootApplication
@RestController
public class PanicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanicApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}


	@PostMapping("/login")
	public String create(@RequestBody String user) {

		LoginApplication login = new LoginApplication();

		if(login.validaLogin(user)){

			return "okok";

		}else{
			return "erro";
		}

		
	}

	@RequestMapping(value = "/products")
   public String getProduct() {
      return "okok";
   }
}