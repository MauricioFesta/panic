/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.services;

import com.mongodb.client.result.UpdateResult;
import com.stock.panic.model.Log;
import com.stock.panic.model.Product;
import com.stock.panic.repository.LogRepositoryInterface;
import com.stock.panic.repository.ProductRepositoryInterface;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.bson.types.ObjectId;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import utils.DateUtil;

/**
 *
 * @author mauri42
 */
public class ProductService {
    
    private final ProductRepositoryInterface poductRepository;
    private final LogRepositoryInterface logRepository;
    
    
    public ProductService(ProductRepositoryInterface poductRepository, LogRepositoryInterface logRepository){
        
        this.poductRepository = poductRepository;
        this.logRepository = logRepository;
        
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
    
    public Product create(String body, HttpServletRequest request) {
        
        JSONObject bodyJson = new JSONObject(body);
        
        HttpSession session=request.getSession();
        
        String conta_id = session.getAttribute("conta_id").toString();
        ObjectId newContaId = new ObjectId(conta_id);
        
        String descricao = bodyJson.getString("descricao");
        String barcode = bodyJson.getString("codBarras");
        int qtd = bodyJson.getInt("qtd");
        int ativo = bodyJson.getInt("ativo");
        
        Product product = new Product(barcode, descricao,qtd,ativo,newContaId);
        
        return poductRepository.create(product);
        
    }
    
    public long count(HttpServletRequest request){
        
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return poductRepository.totalProducts(newContaId);
        
        
    }
    
    public long decrease(String body,HttpServletRequest request) {
        
        JSONObject bodyJson = new JSONObject(body);
        
        String barcode = bodyJson.getString("codBarras");
         
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        String user_id = session.getAttribute("user_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        ObjectId newUserId = new ObjectId(user_id);
        
        UpdateResult result = poductRepository.decreaseProduct(barcode, newContaId);
        DateUtil date = new DateUtil();
     
        Log log = new Log(newUserId,date.now(),barcode,newContaId);
        LogService logService = new LogService(logRepository);
        logService.create(log);
        
        return result.getModifiedCount();
    }
    
    public List<Product> barcodeFilter(String body, HttpServletRequest request){
        
        JSONObject bodyJson = new JSONObject(body);
        String barcode = bodyJson.getString("barcode");
        
        int limit = bodyJson.getInt("limit");
        int page = bodyJson.getInt("page");
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return poductRepository.filterBarcode(barcode,newContaId, limit, page);
        
        
    }
    
    public long barcodeFilterCount(String body, HttpServletRequest request){
        
         JSONObject bodyJson = new JSONObject(body);
        String barcode = bodyJson.getString("barcode");
        
        int limit = bodyJson.getInt("limit");
        int page = bodyJson.getInt("page");
        
        HttpSession session=request.getSession();
        String conta_id = session.getAttribute("conta_id").toString();
        
        ObjectId newContaId = new ObjectId(conta_id);
        
        return poductRepository.filterBarcodeCount(barcode,newContaId, limit, page);
        
    }
    
}
