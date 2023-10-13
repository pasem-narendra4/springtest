package com.centella.backend.service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddUserRequestDTO;
import com.centella.backend.dtos.UpdateUserRequestDTO;

public interface UserService {
	
	public APIResponse addUser(AddUserRequestDTO addUserRequestDTO);

	public APIResponse updateUser(UpdateUserRequestDTO updateUserRequestDTO, String username);

}
