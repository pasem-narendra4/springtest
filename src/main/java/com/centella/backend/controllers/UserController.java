package com.centella.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddUserRequestDTO;
import com.centella.backend.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/adduser")
	public ResponseEntity<APIResponse> addUser(@RequestBody AddUserRequestDTO addUserRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		apiResponse = service.addUser(addUserRequestDTO);
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
}
