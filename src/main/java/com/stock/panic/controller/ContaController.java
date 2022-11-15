package com.stock.panic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.services.ContaService;
import com.stock.panic.model.Conta;
import com.stock.panic.repository.ContaRepositoryInterface;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	private final ContaRepositoryInterface contaRepository;
	
	public ContaController(ContaRepositoryInterface contaRepository) {

		this.contaRepository = contaRepository;
	}

	@PostMapping("/index")
	public List<Conta> login(@RequestBody String user, HttpServletRequest request) {
		
            ContaService conta = new ContaService(contaRepository);

            return conta.getAll();

	}



}