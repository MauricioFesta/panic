package com.stock.panic.model;
import org.json.JSONObject;

public class User {

   public String email;
   public String password;

    public void setFromLogin(String user){

        JSONObject body = new JSONObject(user);

        this.email = body.getString("email");
        this.password = body.getString("senha");

    }

}

