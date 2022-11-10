package com.stock.panic.services;

import java.util.List;

import com.stock.panic.model.Contas;
import com.stock.panic.repository.ContasRepository;

public class ContasService  {

  String body = null;

  private final ContasRepository contaRepository;

	public ContasService(ContasRepository contaRepository) {
        this.contaRepository = contaRepository;
	}

  public List<Contas> getAll(){

        return contaRepository.getAll();
  }



}