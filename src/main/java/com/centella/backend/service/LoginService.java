package com.centella.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.LoginRequestDTO;
import com.centella.backend.entity.User;
import com.centella.backend.repository.UserRepository;
import com.centella.backend.util.JwtUtils;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public APIResponse login(HttpSession session, LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		User user = userRepository.findByUsername(loginRequestDTO.getUsername());
		if(user==null) {
			apiResponse.setData("User login failed");
			apiResponse.setStatus(201);
			return apiResponse;
		}
		
		String password = loginRequestDTO.getPassword();
		String encodedPassword = user.getPassword();
		Boolean isPasswordMatched = passwordEncoder.matches(password, encodedPassword);
		if(!isPasswordMatched) {
			apiResponse.setData("User failed");
			return apiResponse;
		}
		
//		generate jwt token
		String token = jwtUtils.generateJWT(user);
		session.setAttribute("username", loginRequestDTO.getUsername());
//		System.out.print(session.getAttribute("username"));
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		apiResponse.setData(data);
		return apiResponse;
		
	}
	public APIResponse logout(HttpSession session) {
		APIResponse apiResponse = new APIResponse();
		session.invalidate();
		apiResponse.setData("Logged out");
		apiResponse.setStatus(200);
		return apiResponse;
	}
	
}
