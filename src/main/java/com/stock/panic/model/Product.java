/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author mauri42
 */
@Document("products")
public class Product {
    
    @Id
    private String id;
    private String codigo_barras;
    private String descricao;
    private int qtd;
    private int ativo;
    
    public Product(String codigo_barras, String descricao, int qtd, int ativo){
        super();
        this.codigo_barras = codigo_barras;
        this.descricao = descricao;
        this.qtd = qtd;
        this.ativo = ativo;
         
    }
    
    public String getCodBarras(){
        
        return this.codigo_barras;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public int getAtivo(){
        return this.ativo;
    }
    
    public int getQtd(){
        return this.qtd;
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
}
