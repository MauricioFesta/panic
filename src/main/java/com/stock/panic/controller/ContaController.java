package com.stock.panic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.services.ContaService;
import com.stock.panic.model.Usuario;
import com.stock.panic.repository.UsuarioRepositoryInterface;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	private final UsuarioRepositoryInterface contaRepository;
	
	public ContaController(UsuarioRepositoryInterface contaRepository) {

		this.contaRepository = contaRepository;
	}

	@PostMapping("/index")
	public List<Usuario> login(@RequestBody String user, HttpServletRequest request) {
		
            ContaService conta = new ContaService(contaRepository);

            return conta.getAll();

	}



}