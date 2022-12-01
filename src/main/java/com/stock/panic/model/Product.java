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
public class Product {
    
    @Id
    private String id;
    private ObjectId contaId;
    private String barcode;
    private String descricao;
    private int qtd;
    private int ativo;
    private Date insertedAt;
    
    public Product(String barcode, String descricao, int qtd, int ativo, ObjectId contaId, Date insertedAt){
        super();
        this.barcode = barcode;
        this.descricao = descricao;
        this.qtd = qtd;
        this.ativo = ativo;
        this.contaId = contaId;
        this.insertedAt = insertedAt;
    }
    
    public String getId(){
        
        return id;
        
    }
    
    public String getCodBarras(){
        
        return barcode;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public int getAtivo(){
        return ativo;
    }
    
    public int getQtd(){
        return qtd;
    }
    
    public ObjectId getContaId(){
        return contaId;
    }
    
    public void setCodBarras(String barcode){
        
        this.barcode = barcode;
        
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setAtivo(int ativo){
        this.ativo = ativo;
    }
    
    public void setQtd(int qtd){
        this.qtd = qtd;
    } 
    
    public void setContaId(ObjectId conta_id){
        this.contaId = conta_id;
    }
    
    public Date getInsertedAt(){
        
        return insertedAt;
    }
    
    public void setInsertedAt(Date insertedAt){
        
        this.insertedAt = insertedAt;    
    }
    
}
