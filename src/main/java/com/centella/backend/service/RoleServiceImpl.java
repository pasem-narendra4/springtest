package com.centella.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddRoleRequestDTO;
import com.centella.backend.dtos.ChangeRoleRequestDTO;
import com.centella.backend.entity.Role;
import com.centella.backend.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository repo;
	
	public APIResponse addRole(AddRoleRequestDTO addRoleRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
		List<Role> currentRole = repo.existing(addRoleRequestDTO.getUsername(),addRoleRequestDTO.getRole());
		if(currentRole.size()==0) {
			Role role = new Role();
			role.setUsername(addRoleRequestDTO.getUsername());
			role.setRole(addRoleRequestDTO.getRole());
			repo.save(role);
			
			apiResponse.setData("Role created successfully");
			return apiResponse;
		}
		apiResponse.setData("User is already provided a role");
		return apiResponse;
	}
	
	public APIResponse updateRole(ChangeRoleRequestDTO ChangeRoleRequestDTO, String username) {
		APIResponse apiResponse = new APIResponse();
		Role currentRole = repo.findByUsername(username);
		if(currentRole!=null) {
			currentRole.setRole(ChangeRoleRequestDTO.getRole());
			repo.save(currentRole);
			apiResponse.setData("Role updated successfully");
			return apiResponse;
		}
		apiResponse.setData("User is not given a role");
		return apiResponse;
	}
}
