/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author mauri42
 */

@Document("users")
public class User {
    
    @Id
    private String _id;
    private ObjectId contaId;
    private String email;
    private String password;
    private int ativo;
    private Date insertedAt;
    private String nomeCompleto;
    private boolean administrador;
    
   // public User(String email, String password,int ativo,Date insertedAt, String contaId) {
        
      //  this.email = email;
       // this.password = password;
       // this.ativo = ativo;
        //this.insertedAt = insertedAt;
       // this.contaId = contaId;
        
    //}
    
    public String getId() {
        return _id;
    }
        
    public ObjectId getContaId() {
         return contaId;
    }

    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getAtivo(){
        
        return ativo;
    }
    
    public Date getInsertedAt(){
        
        return insertedAt;
    }
    
    public String getNome(){
        return nomeCompleto;
    }
    
    public boolean getAdministrador(){
        return administrador;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setAtivo(int ativo){
        
        this.ativo = ativo;
    }
    
    public void setInsertedAr(Date insertedAt){
        this.insertedAt = insertedAt;
    }
    
    public void setNome(String nome){
        this.nomeCompleto = nome;
    }
    
    public void setContaId(ObjectId contaId){
        this.contaId = contaId;
    }
    
    public void setAdministrador(boolean adm){
        this.administrador = adm;
    }
    
}
