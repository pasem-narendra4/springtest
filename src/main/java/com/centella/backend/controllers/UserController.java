package com.centella.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddUserRequestDTO;
import com.centella.backend.dtos.UpdateUserRequestDTO;
import com.centella.backend.service.UserService;

@RestController
@CrossOrigin
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
	
	
	@PatchMapping("/update/{username}")
	public ResponseEntity<APIResponse> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, @PathVariable String username){
		APIResponse apiResponse = new APIResponse();
		apiResponse = service.updateUser(updateUserRequestDTO, username);
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/sample")
	public String sample() {
		return "Sample";
	}
}
