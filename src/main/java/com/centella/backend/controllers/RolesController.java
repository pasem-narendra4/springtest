package com.centella.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddRoleRequestDTO;
import com.centella.backend.dtos.ChangeRoleRequestDTO;
import com.centella.backend.service.RoleService;


@RestController
public class RolesController {
	
	@Autowired
	RoleService service;
	
	@PostMapping("/addrole")
	public ResponseEntity<APIResponse> addRole(@RequestBody AddRoleRequestDTO addRoleRequestDTO) {
		APIResponse apiResponse = service.addRole(addRoleRequestDTO);
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<APIResponse> updateRole(@RequestBody ChangeRoleRequestDTO changeRoleRequestDTO, @PathVariable String username) {
		APIResponse apiResponse = service.updateRole(changeRoleRequestDTO, username);
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
}
