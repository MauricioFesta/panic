/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.model;

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
    private ObjectId conta_id;
    private String codigo_barras;
    private String descricao;
    private int qtd;
    private int ativo;
    
    public Product(String codigo_barras, String descricao, int qtd, int ativo, ObjectId conta_id){
        super();
        this.codigo_barras = codigo_barras;
        this.descricao = descricao;
        this.qtd = qtd;
        this.ativo = ativo;
        this.conta_id = conta_id;
    }
    
    public String getId(){
        
        return id;
        
    }
    
    public String getCodBarras(){
        
        return codigo_barras;
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
        return conta_id;
    }
    
    public void setCodBarras(String codigo_barras){
        
        this.codigo_barras = codigo_barras;
        
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
        this.conta_id = conta_id;
    }
}