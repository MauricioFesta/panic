package com.stock.panic.model;

import java.util.Date;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mauri42
 */

@Document("logs")
public class Log {
    
    @Id
    public String _id;
    public ObjectId user_id;
    public ObjectId conta_id;
    public Date inserted_at;
    public String barcode;
    
    
    public Log(ObjectId user_id, Date inserted_at,String barcode, ObjectId conta_id){
        
        this.user_id = user_id;
        this.inserted_at = inserted_at;
        this.barcode = barcode;  
        this.conta_id = conta_id;
    }
    
    public ObjectId getUserId(){
        
        return user_id;
    }
    
    public ObjectId getContaId(){
        
        return conta_id;
        
    }
    
    public String getId(){
        return _id;
    }
    
    public void setContaId(ObjectId conta_id){
        
        this.conta_id = conta_id;
    }
    
    
    public Date getInsertedAt(){
        
        return inserted_at;
    }
    
    public String getBarcode(){
        
        return barcode;
    }
    
    public void setUserId(ObjectId user_id){
        
        this.user_id = user_id;
        
    }
    
    
}
