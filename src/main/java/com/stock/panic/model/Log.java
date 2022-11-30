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
    public ObjectId userId;
    public ObjectId contaId;
    public Date insertedAt;
    public String barcode;
    public String nome;
    public String total;
    
    
    public Log(ObjectId userId, Date insertedAt,String barcode, ObjectId contaId){
        
        this.userId = userId;
        this.insertedAt = insertedAt;
        this.barcode = barcode;  
        this.contaId = contaId;
    }
    
    public ObjectId getUserId(){
        
        return userId;
    }
    
    public ObjectId getContaId(){
        
        return contaId;
        
    }
    
    public String getId(){
        return _id;
    }
    
    public void setContaId(ObjectId conta_id){
        
        this.contaId = conta_id;
    }
    
    
    public Date getInsertedAt(){
        
        return insertedAt;
    }
    
    public String getBarcode(){
        
        return barcode;
    }
    
    
    public void setUserId(ObjectId user_id){
        
        this.userId = user_id;
        
    }
    
    public String getTotal(){
        
        return total;
    }
    
       
}
