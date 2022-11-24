package com.stock.panic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("contas")
public class Conta {

	@Id
	private String _id;
	private String email;
	private String password;
	
	public Conta(String email, String password) {
            super();
            this.email = email;
            this.password = password;
	}

	public String getId() {
            return _id;
	}

	public void setId(String id) {
            this._id = id;
	}

	public String getEmail() {
            return email;
	}

	public void setEmail(String email) {
            this.email = email;
	}

	public String getPassword() {
            return password;
	}

	public void setPassword(String password) {
            this.password = password;
	}

}