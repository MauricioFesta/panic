/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;

import com.stock.panic.model.Product;
import com.stock.panic.repository.ProductRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;


/**
 *
 * @author mauri42
 */
@Repository
public class ProductRepository implements ProductRepositoryInterface {
    
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> getAllPaged(int page, int limit, ObjectId conta_id) {
        
        int skip = (page - 1) * limit;
        
        Query query = new Query();
        query.addCriteria(Criteria.where("conta_id").is(conta_id));
        query.skip(skip);
        query.limit(limit);
        
        return mongoTemplate.find(query,Product.class);
    }
    
    @Override
    public Product create(Product product){
                
       return mongoTemplate.save(product);

    }

    
}
