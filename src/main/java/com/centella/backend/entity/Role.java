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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer role_id;
	private String username;
	private String role;
}
