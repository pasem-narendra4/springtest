package com.centella.backend.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.centella.backend.dtos.AddUserRequestDTO;
import com.centella.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("select u from User u where lower(u.username) = lower(:username) and u.password = :password")
	public User findByUsernameIgnoreCaseAndPassword(@Param("username") String username, @Param("password") String password);
}
