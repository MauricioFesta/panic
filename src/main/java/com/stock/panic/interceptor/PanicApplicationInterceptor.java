package com.stock.panic.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class PanicApplicationInterceptor implements HandlerInterceptor {
    
   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {

        HttpSession session=request.getSession();  
        
        String requestPath = request.getServletPath();
        
           
        if(requestPath.equalsIgnoreCase("/login") || requestPath.equalsIgnoreCase("/error")      
          || requestPath.equalsIgnoreCase("/login-mobile")){
        
          return true;

        }
        
        String headerContaID = request.getHeader("AuthorizationContaId");
        
        System.out.println("Conta id from mobile " + headerContaID);
        
        String bearer = request.getHeader("Authorization").trim().replace("Bearer", "").trim();
                
        if(session.getAttribute("conta_id") != null || headerContaID != null) {
                  
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
            
            try {
                    Algorithm algorithm = Algorithm.RSA256(pubKey, privKey);
                    JWTVerifier verifier = JWT.require(algorithm)
                        // specify an specific claim validations
                        .withIssuer("auth0")
                        // reusable verifier instance
                        .build();

                    verifier.verify(bearer);
                                        
                    return true;
                    
                } catch (JWTVerificationException exception){
                  
                   response.sendError(401);
                   return false;
                }
                        
        }
        
      response.sendError(401);
      return false;
   }
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler, ModelAndView modelAndView) throws Exception {
      
      //System.out.println("Post Handle method is Calling");
   }
   @Override
   public void afterCompletion
      (HttpServletRequest request, HttpServletResponse response, Object 
      handler, Exception exception) throws Exception {
      
      //System.out.println("Request and Response is completed");
   }
}