/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.controller;


import com.stock.panic.model.Product;
import com.stock.panic.repository.ProductRepositoryInterface;
import com.stock.panic.services.ProductService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductRepositoryInterface productRepository;
     
    public ProductController(ProductRepositoryInterface productRepository) {
        this.productRepository = productRepository;
	
    }   

    @PostMapping("/index")
    public List<Product> index(@RequestBody String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
         ProductService productService = new ProductService(productRepository);
       
        
        return productService.getPaged(body, request);
		
    }
    
    @PostMapping("/create")
    public boolean create(@RequestBody String body, HttpServletRequest request) throws IOException, NoSuchAlgorithmException,InvalidKeySpecException {
	
        ProductService productService = new ProductService(productRepository);
        
        if(productService.create(body, request) != null){
            
            return true;    
        }   		
        
        return false;
    }
    
    
}