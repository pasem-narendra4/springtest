package com.centella.backend.service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddRoleRequestDTO;
import com.centella.backend.dtos.ChangeRoleRequestDTO;

public interface RoleService {
	public APIResponse addRole(AddRoleRequestDTO addRoleRequestDTO);
	public APIResponse updateRole(ChangeRoleRequestDTO changeRoleRequestDTO, String username);
}
