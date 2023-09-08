package com.centella.backend.service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddUserRequestDTO;

public interface UserService {
	
	public APIResponse addUser(AddUserRequestDTO addUserRequestDTO);

}
