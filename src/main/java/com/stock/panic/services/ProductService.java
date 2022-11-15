/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.services;

import com.stock.panic.model.Product;
import com.stock.panic.repository.ProductRepositoryInterface;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.bson.types.ObjectId;

/**
 *
 * @author mauri42
 */
public class ProductService {
    
    private final ProductRepositoryInterface poductRepository;
    
    
    public ProductService(ProductRepositoryInterface poductRepository){
        
        this.poductRepository = poductRepository;
        
    }
    
    public List<Product> getPaged( String body, HttpServletRequest request){
        
         
        JSONObject bodyJson = new JSONObject(body);
        
        HttpSession session=request.getSession(); 
        
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
	
        int page = bodyJson.getInt("page");
        int limit = bodyJson.getInt("limit");
        
        return poductRepository.getAllPaged(page, limit, newContaId);
        
    }
    
}
