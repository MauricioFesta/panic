package com.stock.panic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
public class Usuario {

	@Id
	private String _id;
        private String conta_id;
	private String email;
	private String password;
	
	public Usuario(String email, String password) {
            super();
            this.email = email;
            this.password = password;
	}

	public String getId() {
            return _id;
	}
        
        public String getContaId() {
            return conta_id;
	}


	public void setId(String id) {
            this._id = id;
	}
        
        public void setContaId(String conta_id) {
            this.conta_id = conta_id;
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