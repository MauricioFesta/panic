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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.stock.panic.repository.UsuarioRepositoryInterface;
/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/")
public class LoginController {

    private final UsuarioRepositoryInterface contaRepository;
	
    public LoginController(UsuarioRepositoryInterface contasRepository) {
	this.contaRepository = contasRepository;	
    }   

    @PostMapping("/login")
    public String login(@RequestBody String user, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
        LoginService login = new LoginService(contaRepository);

        login.valida(user, request);
                
        JSONObject data = new JSONObject();
    
        if(login.getIsOk()){
                        
            data.put("status", "ok");
            data.put("hash", login.getToken());
                        
            return data.toString();

	}else{
          
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
	}		
    }
    
    @PostMapping("/login-mobile")
    public String loginMobile(@RequestBody String user, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
        LoginService login = new LoginService(contaRepository);

        login.validaMobile(user, request);
                
        JSONObject data = new JSONObject();

        if(login.getIsOk()){

            data.put("status", "ok");
            data.put("hash", login.getToken());
            data.put("conta_id", login.getContaId());
            data.put("user_id", login.getUserId());
                        
            return data.toString();

	}else{
          
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
	}		
    }
}
