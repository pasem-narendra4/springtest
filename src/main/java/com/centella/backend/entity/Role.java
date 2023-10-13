package com.centella.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Table(name = "Roles")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class Role {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer role_id;
	private String username;
	private String role;
	public Role(Integer role_id, String username, String role) {
		super();
		this.role_id = role_id;
		this.username = username;
		this.role = role;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Role() {
		super();
	}
	
}
