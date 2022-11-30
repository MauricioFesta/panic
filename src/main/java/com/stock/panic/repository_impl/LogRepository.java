/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;

import com.stock.panic.model.Log;
import com.stock.panic.repository.LogRepositoryInterface;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.CountOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
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
        
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));
        SkipOperation skipStage = Aggregation.skip(skip);
        LimitOperation limitStage = Aggregation.limit(limit);
        LookupOperation lookupStage = Aggregation.lookup("usuarios", "userId", "_id", "usuarios");
                
        ProjectionOperation projectStage = Aggregation.project("usuarios.nome", "barcode", "insertedAt");
   
        Aggregation aggregation = Aggregation.newAggregation(matchStage, skipStage, limitStage, lookupStage,projectStage);
          
        AggregationResults<Log> output = mongoTemplate.aggregate(aggregation, "logs", Log.class);
        
        return output.getMappedResults();
 
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
    
    @Override
    public List<Log> filterDate(int page, int limit, ObjectId conta_id, LocalDateTime dateStart, LocalDateTime dateEnd){
        
        int skip = (page - 1) * limit;
       
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));  
        MatchOperation matchDateStartStage = Aggregation.match(new Criteria("insertedAt").gte(dateStart)); 
        MatchOperation matchDateEndStage = Aggregation.match(new Criteria("insertedAt").lte(dateEnd)); 
        SkipOperation skipStage = Aggregation.skip(skip);
        LimitOperation limitStage = Aggregation.limit(limit);
        LookupOperation lookupStage = Aggregation.lookup("usuarios", "userId", "_id", "usuarios");
                
        ProjectionOperation projectStage = Aggregation.project("usuarios.nome", "barcode", "insertedAt");
        
        Aggregation aggregation = Aggregation.newAggregation(matchStage,matchDateStartStage,matchDateEndStage,skipStage,limitStage,lookupStage,projectStage);
          
        AggregationResults<Log> output = mongoTemplate.aggregate(aggregation, "logs", Log.class);
        
        return output.getMappedResults();
        
    }
    
    @Override
    public long filterDateCount(ObjectId conta_id, LocalDateTime dateStart, LocalDateTime dateEnd){
                          
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));  
        MatchOperation matchDateStartStage = Aggregation.match(new Criteria("insertedAt").gte(dateStart)); 
        MatchOperation matchDateEndStage = Aggregation.match(new Criteria("insertedAt").lte(dateEnd)); 
      
        CountOperation countStage = new  CountOperation("total");
        
        Aggregation aggregation = Aggregation.newAggregation(matchStage,matchDateStartStage,matchDateEndStage,countStage);
        
        AggregationResults<Log> output = mongoTemplate.aggregate(aggregation, "logs", Log.class);
        
        if(output.getMappedResults().isEmpty()){
            
            return 0;
            
        }else{
            
            return Long.parseLong(output.getMappedResults().get(0).total);     
        }
                 
    }
}
