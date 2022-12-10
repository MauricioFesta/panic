/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;


import com.stock.panic.model.Product;
import com.stock.panic.repository.ReportsRepositoryInterface;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri42
 */

@Repository
public class ReportsRepository implements ReportsRepositoryInterface {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public List<Product> positiveStock(ObjectId contaId) {
        
        Query query = new Query();
        query.addCriteria( Criteria.where("contaId").is(contaId));
        query.addCriteria( Criteria.where("qtd").gte(1));
        
       return mongoTemplate.find(query, Product.class);
                 
    }
    
}
