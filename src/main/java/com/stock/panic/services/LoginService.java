package com.stock.panic.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.stock.panic.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.stock.panic.repository.ContasRepository;
import com.stock.panic.model.Contas;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;


public class LoginService  {

  String body = null;

  private final ContasRepository contaRepository;

	public LoginService(ContasRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

  public String valida(String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {

        User newUser = new User(body);
             
        Contas conta = contaRepository.getLogin(newUser.getEmail(), newUser.getPassword());

        if(conta != null){

            HttpSession session=request.getSession();  
                  
            Path pathPub = Paths.get("/home/mauri42/.ssh/public_key.der");
            Path pathPriv = Paths.get("/home/mauri42/.ssh/private_key.der");
            
            byte[] bytesPub = Files.readAllBytes(pathPub);
            byte[] bytesPriv = Files.readAllBytes(pathPriv);
        
            PKCS8EncodedKeySpec ksPriv =
                     new PKCS8EncodedKeySpec(bytesPriv);
            KeyFactory kfPriv = KeyFactory.getInstance("RSA");
            
            RSAPrivateKey privKey = (RSAPrivateKey) kfPriv.generatePrivate(ksPriv);
            
            
            X509EncodedKeySpec ksPub = new X509EncodedKeySpec(bytesPub);
            KeyFactory kfPub = KeyFactory.getInstance("RSA");
            RSAPublicKey pubKey = (RSAPublicKey) kfPub.generatePublic(ksPub);
       
            Date date = new Date();
            
            Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR_OF_DAY, 1);

            try {
                Algorithm algorithm = Algorithm.RSA256(pubKey, privKey);
                String token = JWT.create().withExpiresAt(calendar.getTime())
                    .withIssuer("auth0")
                    .sign(algorithm);
                
                
                session.setAttribute("conta_id",conta.getId()); 
                return token;
                
            } catch (JWTCreationException exception){
                
                return "";
                
                // Invalid Signing configuration / Couldn't convert Claims.
            }

        }else{

            return "";
        }

  }


}