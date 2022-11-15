package com.stock.panic.services;

import java.util.List;

import com.stock.panic.model.Conta;
import com.stock.panic.repository.ContaRepositoryInterface;

public class ContasService  {

    private final ContaRepositoryInterface contaRepository;

    public ContasService(ContaRepositoryInterface contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> getAll(){

        return contaRepository.getAll();
    }



}