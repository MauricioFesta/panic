/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;

import com.stock.panic.model.Log;
import com.stock.panic.model.User;
import com.stock.panic.repository.UserRepositoryInterface;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri42
 */

@Repository
public class UserRepository implements UserRepositoryInterface {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public List<User> getPaged(int page, int limit, ObjectId conta_id){
        
        int skip = (page - 1) * limit;
       
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));  
        SkipOperation skipStage = Aggregation.skip(skip);
        LimitOperation limitStage = Aggregation.limit(limit);
        
        
        Aggregation aggregation = Aggregation.newAggregation(matchStage,skipStage,limitStage);
          
        AggregationResults<User> output = mongoTemplate.aggregate(aggregation, "users", User.class);
        
        return output.getMappedResults();
        
    }
    
    
    @Override
    public User getLogin(String email){
        
        System.out.println("email::::::::: " + email);

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
            //query.addCriteria(Criteria.where("senha").is(password));

        return mongoTemplate.findOne(query, User.class);
    }

}
