/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.controller;

import com.stock.panic.model.User;
import com.stock.panic.repository.UserRepositoryInterface;
import com.stock.panic.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    private final UserRepositoryInterface userRepository;
    
    public UserController(UserRepositoryInterface userRepository){
        
        this.userRepository = userRepository;
    }
    
    
    @PostMapping("/index")
    public List<User> index(@RequestBody String body, HttpServletRequest request){
        
        UserService userService = new UserService(userRepository);
        
        return userService.getPaged(body, request);
        
    }
    
   
    
}
