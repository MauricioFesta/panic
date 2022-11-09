package com.stock.panic;
import com.stock.panic.model.User;

public class LoginApplication  {

  String body = null;
    
  public boolean validaLogin(String body){

        User newUser = new User();
        newUser.setFromLogin(body);
        
        if(newUser.email.equals("festamauricio42@gmail.com") && newUser.password.equals("123")){
            return true;

        }else{

            return false;
        }

  }


}