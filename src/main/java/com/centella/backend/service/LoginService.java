package com.centella.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.LoginRequestDTO;
import com.centella.backend.entity.User;
import com.centella.backend.repository.UserRepository;
import com.centella.backend.util.JwtUtils;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		User user = userRepository.findByUsername(loginRequestDTO.getUsername());
		if(user==null) {
			apiResponse.setData("User login failed");
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
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		apiResponse.setData(data);
		return apiResponse;
		
	}
}
