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
import com.stock.panic.services.ContasService;
import com.stock.panic.model.Contas;
import com.stock.panic.repository.ContasRepository;

@RestController
@RequestMapping(value = "/contas")
public class ContasController {

	private final ContasRepository contaRepository;
	
	public ContasController(ContasRepository contaRepository) {

		this.contaRepository = contaRepository;
	}

	@PostMapping("/all")
	public List<Contas> login(@RequestBody String user, HttpServletRequest request) {
		
		ContasService conta = new ContasService(contaRepository);

		return conta.getAll();

	}



}