package com.stock.panic.services;

import com.stock.panic.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.stock.panic.repository.ContasRepository;
import com.stock.panic.model.Contas;


public class LoginService  {

  String body = null;

  private final ContasRepository contaRepository;

	public LoginService(ContasRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

  public boolean valida(String body, HttpServletRequest request){

        User newUser = new User(body);
        //newUser.setFromLogin(body);
        Contas conta = contaRepository.getLogin(newUser.getEmail(), newUser.getPassword());

        if(conta != null){
  
            HttpSession session=request.getSession();  
             
            session.setAttribute("conta_id","888888888");  

            return true;

        }else{

            return false;
        }

  }


}