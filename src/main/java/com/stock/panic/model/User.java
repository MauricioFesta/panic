package com.stock.panic.model;
import org.json.JSONObject;

public class User {

   public String email;
   public String password;

   public User(String body) {

        JSONObject user = new JSONObject(body);

       this.email = user.getString("email");
       this.password = user.getString("senha");
   }

    public String getEmail(){

        return this.email;

    }

    public String getPassword(){

        return this.password;
        
    }

}

