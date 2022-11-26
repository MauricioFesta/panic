/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;

import com.stock.panic.model.Log;
import com.stock.panic.model.Product;
import com.stock.panic.repository.LogRepositoryInterface;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri42
 */
 
@Repository
public class LogRepository implements LogRepositoryInterface {
    
   
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Log> getAllPaged(int page, int limit, ObjectId conta_id) {
        
        int skip = (page - 1) * limit;
        
        Query query = new Query();
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        query.skip(skip);
        query.limit(limit);
        
        return mongoTemplate.find(query,Log.class);
    }
    
    @Override
    public Log create(Log log){
                
       return mongoTemplate.save(log);

    }
    
    @Override
    public long totalLogs(ObjectId conta_id){
        
        Query query = new Query();
        
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        
        return mongoTemplate.count(query, Log.class);   
    }
    
    @Override
    public long totalProducts(ObjectId conta_id){
        
        Query query = new Query();
        
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        
        return mongoTemplate.count(query, Log.class);
        
    }
}
