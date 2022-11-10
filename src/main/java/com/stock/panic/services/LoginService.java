package com.stock.panic.services;
import com.stock.panic.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.stock.panic.repository.LoginRepository;
import com.stock.panic.model.Contas;


public class LoginService  {

  String body = null;

  private final LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

  public boolean validaLogin(String body, HttpServletRequest request){

        User newUser = new User();
        newUser.setFromLogin(body);

  
        if(newUser.email.equals("festamauricio42@gmail.com") && newUser.password.equals("123")){
  
            HttpSession session=request.getSession();  
             
            session.setAttribute("conta_id","888888888");  

            return true;

        }else{

            return false;
        }

  }


}