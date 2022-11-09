package com.stock.panic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PanicApplicationInterceptor implements HandlerInterceptor {

   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {

        HttpSession session=request.getSession();  
        System.out.println("222222222222"); 
        
        if(session.getAttribute("conta_id") == null){
            
            System.out.println("Não existe sessao"); 
            
            //session.setAttribute("conta_id","888888888");  
            
        }else{

          System.out.println(session.getAttribute("conta_id"));  
        }
        
         System.out.println("end"); 

          
      System.out.println("Pre Handle method is Calling");
      return true;
   }
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler, ModelAndView modelAndView) throws Exception {
      
      System.out.println("Post Handle method is Calling");
   }
   @Override
   public void afterCompletion
      (HttpServletRequest request, HttpServletResponse response, Object 
      handler, Exception exception) throws Exception {
      
      System.out.println("Request and Response is completed");
   }
}