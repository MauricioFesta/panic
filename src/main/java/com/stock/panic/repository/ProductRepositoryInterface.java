/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository;

import com.mongodb.client.result.UpdateResult;
import com.stock.panic.model.Product;
import java.util.List;
import org.bson.types.ObjectId;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author mauri42
 */
public interface ProductRepositoryInterface {
    
    
    List<Product> getAllPaged(int page, int limit, ObjectId conta_id);
    Product create(Product product);
    long totalProducts(ObjectId conta_id);
    UpdateResult decreaseProduct(String barcode, ObjectId conta_id);
    
}
