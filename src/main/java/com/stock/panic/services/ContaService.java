package com.stock.panic.services;

import java.util.List;

import com.stock.panic.model.Usuario;
import com.stock.panic.repository.UsuarioRepositoryInterface;

public class ContaService  {

    private final UsuarioRepositoryInterface contaRepository;

    public ContaService(UsuarioRepositoryInterface contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Usuario> getAll(){

        return contaRepository.getAll();
    }



}