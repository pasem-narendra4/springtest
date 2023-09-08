package com.centella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centella.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(value="select * from roles where username=?1 and role=?2", nativeQuery=true)
	List<Role> existing(String username, String role);

	Role findByUsername(String username);

}
