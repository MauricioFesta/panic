package com.stock.panic.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.stock.panic.model.User;
import com.stock.panic.repository.UserRepositoryInterface;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mauri42
 */
public class UserService {
    
    private final UserRepositoryInterface userRepository;
    
    public UserService(UserRepositoryInterface userRepository){
        this.userRepository = userRepository;
    }
    
    public List<User> getPaged(String body, HttpServletRequest request){
        
        JSONObject bodyJson = new JSONObject(body);
        
        int page = bodyJson.getInt("page");
        int limit = bodyJson.getInt("limit");
        
         
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return userRepository.getPaged(page, limit, newContaId);
    }
    
    public User create(String body,HttpServletRequest request){
        
        JSONObject bodyJson = new JSONObject(body);
        
        HttpSession session=request.getSession();
        
        String contaId = session.getAttribute("conta_id").toString();
        String email = bodyJson.getString("email");
        String password = bodyJson.getString("password");
        String nomeCompleto = bodyJson.getString("nomeCompleto");
        boolean adm = bodyJson.getBoolean("adm");
        
        
        User user = new User();
        
        String bcryptHashString = BCrypt.withDefaults().hashToString(14, password.toCharArray());
        
        user.setEmail(email);
        user.setPassword(bcryptHashString);
        user.setNome(nomeCompleto);
        user.setContaId(new ObjectId(contaId));
        user.setAtivo(1);
        user.setAdministrador(adm);
        
        return userRepository.create(user);
    }
    
    
    
}
