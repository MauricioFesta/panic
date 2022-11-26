/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.controller;


import com.stock.panic.model.Product;
import com.stock.panic.repository.LogRepositoryInterface;
import com.stock.panic.repository.ProductRepositoryInterface;
import com.stock.panic.services.ProductService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductRepositoryInterface productRepository;
    private final LogRepositoryInterface logRepository;
     
    public ProductController(ProductRepositoryInterface productRepository, LogRepositoryInterface logRepository) {
        this.productRepository = productRepository;
        this.logRepository = logRepository;
	
    }   

    @PostMapping("/index")
    public List<Product> index(@RequestBody String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
         ProductService productService = new ProductService(productRepository,logRepository);
       
        return productService.getPaged(body, request);
		
    }
    
    @PostMapping("/create")
    public boolean create(@RequestBody String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
        ProductService productService = new ProductService(productRepository,logRepository);
        
        if(productService.create(body, request) != null){
            
            return true;    
        }   		
        
        return false;
    }
    
    @GetMapping("/count")
    public long count(HttpServletRequest request){
        
        ProductService productService = new ProductService(productRepository,logRepository);
      
        return productService.count(request); 
    }
    
    @PostMapping("/decrease")
    public boolean decrease(@RequestBody String body,HttpServletRequest request) {
        
        ProductService productService = new ProductService(productRepository,logRepository);
      
        long resp = productService.decrease(body,request); 
               
        if(resp == 0){
            
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);   
        }else{
            
            return true;
        }
            
    }
    
}
