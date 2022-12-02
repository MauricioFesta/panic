/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository;

import com.stock.panic.model.User;
import java.util.List;
import org.bson.types.ObjectId;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author mauri42
 */

public interface UserRepositoryInterface {
    
    List<User> getPaged(int page, int limit, ObjectId conta_id);
    User getLogin(String email);
    
}
