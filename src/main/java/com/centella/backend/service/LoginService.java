package com.centella.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	private JwtUtils jwtUtils;
	
	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		User user = userRepository.findByUsernameIgnoreCaseAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		System.out.print(user);
		if(user==null) {
			apiResponse.setData("User login failed");
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
