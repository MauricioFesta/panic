package com.stock.panic.repository;

import java.util.List;

import com.stock.panic.model.Conta;

public interface ContaRepositoryInterface {

    List<Conta> getAll();
    Conta getLogin(String email);

}