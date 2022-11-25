package com.stock.panic.repository;

import java.util.List;

import com.stock.panic.model.Usuario;

public interface UsuarioRepositoryInterface {

    List<Usuario> getAll();
    Usuario getLogin(String email);

}