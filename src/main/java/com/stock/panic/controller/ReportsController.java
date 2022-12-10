/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.controller;

import com.stock.panic.repository.ReportsRepositoryInterface;
import com.stock.panic.services.ReportsService;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.data.redis.connection.ReactiveStreamCommands.AddStreamRecord.body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauri42
 */

@RestController
@RequestMapping(value = "/reports")
public class ReportsController {
    
    private final ReportsRepositoryInterface reportsRepository;
    
    public ReportsController(ReportsRepositoryInterface reportsRepository){
        this.reportsRepository = reportsRepository;
    }
    
    @GetMapping("/positive")
    public String positiveStock(HttpServletRequest request){
        
        ReportsService reportsService = new ReportsService(reportsRepository);
        
        return reportsService.positiveStock(request);
    }
    
    
}
