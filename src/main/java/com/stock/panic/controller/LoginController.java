package com.stock.panic.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.services.LoginService;
import com.stock.panic.repository.ContasRepository;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.json.JSONObject;


@RestController
@RequestMapping(value = "/")
public class LoginController {

	private final ContasRepository contasRepository;
	
	public LoginController(ContasRepository contasRepository) {
		this.contasRepository = contasRepository;
	
	}   

	@PostMapping("/login")
	public String login(@RequestBody String user, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
		LoginService login = new LoginService(contasRepository);

		String hash = login.valida(user, request);
                
                JSONObject data = new JSONObject();

		if(hash != ""){
                        
                    data.put("status", "ok");
                    data.put("hash", hash);
                        
                    return data.toString();

		}else{
			return "erro";
		}

		
	}



}
