/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.cron;

import java.io.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * @author mauri42
 */


@Component
public class Cron {
    
    @Scheduled(cron = "*/5 * * * * *")
    public void createHash() throws IOException, InterruptedException {
      
        
        
    }
   
    
}
