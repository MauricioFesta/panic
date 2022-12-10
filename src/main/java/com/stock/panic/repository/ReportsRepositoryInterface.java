/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.repository;

import com.stock.panic.model.Product;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mauri42
 */
public interface ReportsRepositoryInterface {
    
    List<Product> positiveStock(ObjectId contaId);
    
}
