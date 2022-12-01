/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository_impl;

import com.mongodb.client.result.UpdateResult;
import com.stock.panic.model.Product;
import com.stock.panic.model.ProductCount;
import com.stock.panic.repository.ProductRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.CountOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        query.skip(skip);
        query.limit(limit);
        
        return mongoTemplate.find(query,Product.class);
    }
    
    @Override
    public Product create(Product product){
                
       return mongoTemplate.save(product);

    }
    
    @Override
    public long totalProducts(ObjectId conta_id){
        
        Query query = new Query();
        
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        
        return mongoTemplate.count(query, Product.class);
        
    }
    
    @Override
    public UpdateResult decreaseProduct(String barcode, ObjectId conta_id) {
        
        Query query = new Query();
            query.addCriteria(Criteria.where("barcode").is(barcode));
        query.addCriteria(Criteria.where("contaId").is(conta_id));
        
        Update update = new Update();
        update.inc("qtd", -1);
        
       return mongoTemplate.updateFirst(query, update, Product.class);
        
    }
    
    @Override
    public List<Product> filterBarcode(String barcode, ObjectId conta_id, int limit, int page){
        
        int skip = (page - 1) * limit;
        
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));  
        MatchOperation matchRegexStage = Aggregation.match(new Criteria("barcode").regex(barcode));
        SkipOperation skipStage = Aggregation.skip(skip);
        LimitOperation limitStage = Aggregation.limit(limit);
        
        Aggregation aggregation = Aggregation.newAggregation(matchStage,matchRegexStage, skipStage, limitStage);
          
        AggregationResults<Product> output = mongoTemplate.aggregate(aggregation, "products", Product.class);
        
        return output.getMappedResults();
        
    }
    
    @Override
    public long filterBarcodeCount(String barcode, ObjectId conta_id, int limit, int page){
        
        int skip = (page - 1) * limit;
        
        MatchOperation matchStage = Aggregation.match(new Criteria("contaId").is(conta_id));  
        MatchOperation matchRegexStage = Aggregation.match(new Criteria("barcode").regex(barcode));
        SkipOperation skipStage = Aggregation.skip(skip);
        LimitOperation limitStage = Aggregation.limit(limit);
        
        CountOperation countStage = new  CountOperation("total");
        
        Aggregation aggregation = Aggregation.newAggregation(matchStage,matchRegexStage, skipStage, limitStage, countStage);
          
        AggregationResults<ProductCount> output = mongoTemplate.aggregate(aggregation, "products", ProductCount.class);
        
         if(output.getMappedResults().isEmpty()){
            
            return 0;
            
        }else{
            
            return Long.parseLong(output.getMappedResults().get(0).total);     
        }
        
    }

    
}
