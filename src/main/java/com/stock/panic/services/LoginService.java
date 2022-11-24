package com.stock.panic.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.stock.panic.model.Conta;
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
import org.json.JSONObject;
import com.stock.panic.repository.ContaRepositoryInterface;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;


public class LoginService  {

    private final ContaRepositoryInterface contaRepository;
    private boolean isOk; 
    private String token;
    private String contaId;

    public LoginService(ContaRepositoryInterface contaRepository) {
	this.contaRepository = contaRepository;
        this.isOk = false;
        this.token = "";
        this.contaId = "";
    }
    
    public void valida(String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {

        JSONObject user = new JSONObject(body);
      
        //Conta newUser = new Conta(user.getString("email"), user.getString("senha"));
        
        //String bcryptHashString = BCrypt.withDefaults().hashToString(14, user.getString("senha").toCharArray());
        
             
        Conta conta = contaRepository.getLogin(user.getString("email"));

        if(conta != null){
            
            BCrypt.Result result = BCrypt.verifyer().verify(user.getString("senha").toCharArray(), conta.getPassword());

          if(result.verified){
              
            HttpSession session=request.getSession();  
                  
            Path pathPub = Paths.get("/opt/tomcat/public_key.der");
            Path pathPriv = Paths.get("/opt/tomcat/private_key.der");
            
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
                this.setIsOk(true);
                this.setToken(token);
               
                
            } catch (JWTCreationException exception){
                this.setIsOk(false);
            
            }

              
          }else{
              
             this.setIsOk(true);
          }
          
        }else{
            this.setIsOk(false);
        }
    }
    
    public void validaMobile(String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
     
          JSONObject user = new JSONObject(body);
               
        Conta conta = contaRepository.getLogin(user.getString("email"));

        if(conta != null){
            
            BCrypt.Result result = BCrypt.verifyer().verify(user.getString("senha").toCharArray(), conta.getPassword());

          if(result.verified){
              
            HttpSession session=request.getSession();  
                  
            Path pathPub = Paths.get("/opt/tomcat/public_key.der");
            Path pathPriv = Paths.get("/opt/tomcat/private_key.der");
            
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
              
                this.setIsOk(true);
                this.setContaId(conta.getId());
                this.setToken(token);
                
            } catch (JWTCreationException exception){
                this.setIsOk(false);
             
            }

              
          }else{
              this.setIsOk(false);
          }
                  
        }else{
            this.setIsOk(false);
        }
        
    }
    
    private void setIsOk(boolean ok){
        
        this.isOk = ok;   
    }
    
    public boolean getIsOk(){
        
        return isOk;
    }
    
    public void setToken(String tk) {
        
        this.token = tk;   
    }
    
    public String getToken() {
        return token;
    }
    
    public void setContaId(String id){
        this.contaId = id;
    }
    
    public String getContaId() {
        return contaId;
    }
}