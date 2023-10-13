package com.centella.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.centella.backend.common.APIResponse;
import com.centella.backend.dtos.AddUserRequestDTO;
import com.centella.backend.dtos.UpdateUserRequestDTO;
import com.centella.backend.entity.User;
import com.centella.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public APIResponse addUser(AddUserRequestDTO addUserRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		User isExisting = repo.findByUsernameIgnoreCaseAndPassword(addUserRequestDTO.getUsername(), addUserRequestDTO.getPassword());
		if(isExisting==null) {
			User newUser = new User();
			newUser.setUsername(addUserRequestDTO.getUsername());
			newUser.setEmailId(addUserRequestDTO.getEmailId());
			String encodedStringPassword = passwordEncoder.encode(addUserRequestDTO.getPassword());
			newUser.setPassword(encodedStringPassword);
			repo.save(newUser);
			apiResponse.setData("User created successfully");
			return apiResponse;
		}
		apiResponse.setData("Already exists");
		return apiResponse;
	}

	public APIResponse updateUser(UpdateUserRequestDTO updateUserRequestDTO, String username) {
		APIResponse apiResponse = new APIResponse();
		User currentUser = repo.findByUsername(username);
		if(currentUser!=null) {
			currentUser.setName(updateUserRequestDTO.getName());
			currentUser.setCity(updateUserRequestDTO.getCity());
			currentUser.setCountry(updateUserRequestDTO.getCountry());
			currentUser.setEmailId(updateUserRequestDTO.getEmailId());
			repo.save(currentUser);
			apiResponse.setData("User updated successfully");
			return apiResponse;
		}
		apiResponse.setData("User does not exists");
		return apiResponse;
		
	}

}
