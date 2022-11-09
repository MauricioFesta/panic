package com.stock.panic.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class PanicApplicationAppConfig extends WebMvcConfigurerAdapter {
   @Autowired
   PanicApplicationInterceptor panicApplicationInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(panicApplicationInterceptor);
   }
}