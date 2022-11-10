package com.stock.panic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.stock.panic.repository.ContasRepository;
import com.stock.panic.model.Contas;


@RestController
@RequestMapping(value = "/")
public class LoginController {

	private final ContasRepository contasRepository;
	
	public LoginController(ContasRepository contasRepository) {
		this.contasRepository = contasRepository;
	
	}

	@PostMapping("/login")
	public String login(@RequestBody String user, HttpServletRequest request) {
		
		LoginService login = new LoginService(contasRepository);

		if(login.valida(user, request)){

			return "ok";

		}else{
			return "erro";
		}

		
	}



}