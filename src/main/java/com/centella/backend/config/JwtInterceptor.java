package com.centella.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.centella.backend.util.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends WebRequestHandlerInterceptorAdapter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public JwtInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String auth = request.getHeader("authorization");
		
		if (!(request.getRequestURI().contains("login"))) {
			jwtUtils.verify(auth);
		}
		
		return super.preHandle(request, response, handler);
	}
}
