package com.centella.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.LoginRequestDTO;
import com.centella.backend.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
}
