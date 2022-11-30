/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.services;

import com.stock.panic.model.Log;
import com.stock.panic.repository.LogRepositoryInterface;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author mauri42
 */

public class LogService {
    
    private final LogRepositoryInterface logRepository;
    
    
    public LogService(LogRepositoryInterface logRepository){
        
        this.logRepository = logRepository;
        
    }
    
    
    public List<Log>  getPaged(String body, HttpServletRequest request){
        
        JSONObject bodyJson = new JSONObject(body);
        
        HttpSession session=request.getSession(); 
        
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
	
        int page = bodyJson.getInt("page");
        int limit = bodyJson.getInt("limit");
        
        return logRepository.getAllPaged(page, limit, newContaId);
    }
    
    public long count(HttpServletRequest request){
        
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return logRepository.totalProducts(newContaId);    
    }
    
    
    public void create(Log log){
        
        logRepository.create(log);    
    }
    
    public List<Log> filterDate(String body,HttpServletRequest request) {
     
        JSONObject obj = new JSONObject(body);
        
        int limit = obj.getInt("limit");
        int page = obj.getInt("page");
               
        LocalDateTime dateStart =  LocalDateTime.parse((String) obj.get("startDate"));
        LocalDateTime dateEnd = LocalDateTime.parse((String) obj.get("endDate"));
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return logRepository.filterDate(page, limit,newContaId, dateStart, dateEnd);
        
      
    }
    
        public long filterDateCount(String body,HttpServletRequest request) {
     
        JSONObject obj = new JSONObject(body);
              
        LocalDateTime dateStart =  LocalDateTime.parse((String) obj.get("startDate"));
        LocalDateTime dateEnd = LocalDateTime.parse((String) obj.get("endDate"));
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return logRepository.filterDateCount(newContaId, dateStart, dateEnd);
        
      
    }
}
