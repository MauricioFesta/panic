package com.stock.panic;
import com.stock.panic.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginApplication  {

  String body = null;
    
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