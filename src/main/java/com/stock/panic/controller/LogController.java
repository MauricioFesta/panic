/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.controller;

import com.stock.panic.model.Log;
import com.stock.panic.repository.LogRepositoryInterface;
import com.stock.panic.services.LogService;
import com.stock.panic.services.ProductService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/logs")
public class LogController {
    
    private final LogRepositoryInterface logRepository;
     
    public LogController(LogRepositoryInterface logRepository) {
        this.logRepository = logRepository;
    } 
    
    @PostMapping("/index")
    public List<Log> index(@RequestBody String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
         LogService logService = new LogService(logRepository);
       
        return logService.getPaged(body, request);
    }
    
     @GetMapping("/count")
    public long count(HttpServletRequest request){
        
        LogService logService = new LogService(logRepository);
      
        return logService.count(request); 
    }
}
