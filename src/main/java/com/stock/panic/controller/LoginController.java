package com.stock.panic.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.stock.panic.services.LoginService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.json.JSONObject;
import com.stock.panic.repository.ContaRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/")
public class LoginController {

    private final ContaRepositoryInterface contaRepository;
	
    public LoginController(ContaRepositoryInterface contasRepository) {
	this.contaRepository = contasRepository;	
    }   

    @PostMapping("/login")
    public String login(@RequestBody String user, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
        LoginService login = new LoginService(contaRepository);

        String hash = login.valida(user, request);
                
        JSONObject data = new JSONObject();

        if(hash != ""){
                        
            data.put("status", "ok");
            data.put("hash", hash);
                        
            return data.toString();

	}else{
          
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
	}		
    }
}
