package com.stock.panic.repository;

import java.util.List;

import com.stock.panic.model.Contas;

public interface ContasRepository {

	List<Contas> getAll();
	Contas getLogin(String email, String senha);

}