package com.centella.backend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.centella.backend.config.JwtInterceptor;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {
	
	@Autowired
	JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(jwtInterceptor);
//		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
