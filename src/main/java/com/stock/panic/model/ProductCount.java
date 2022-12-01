/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author mauri42
 */
@Document("products")
public class ProductCount {
    
    @Id
    private String id;
    public String total;
    
   public String getTotal(){
      
    return total;
  }
}
